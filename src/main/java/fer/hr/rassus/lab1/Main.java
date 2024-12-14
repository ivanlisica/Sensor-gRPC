package fer.hr.rassus.lab1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static void sensorActive(GrpcClient grpcClient,RetrofitClient retrofitClient) {
        if (retrofitClient.getSensorId() == null) {
            log.error("Sensor not registered");
            return;
        }
        log.info("GPRC CLIENT: {}", grpcClient);
        Long sensorId = retrofitClient.getSensorId();
        SensorDTO nearestNeighbor = retrofitClient.getNearestNeighbor();
        long startTime = retrofitClient.getStartTime();
        while (true) {
            try {
                long secondRunning = (System.currentTimeMillis() - startTime) / 1000;
                int row = (int) (secondRunning % 100) + 1;

                MeasurementDTO measurementDTO = retrofitClient.readFromCSV(row);
                if (nearestNeighbor != null && grpcClient != null) {
                    MeasurementDTO neighborMeasurment =  grpcClient.getNeighbourMeasurement(sensorId);
                    measurementDTO = retrofitClient.calibrateMeasurement(measurementDTO, neighborMeasurment);

                }
                retrofitClient.sendMeasurement(measurementDTO);


                Thread.sleep(10000);

            } catch (InterruptedException e) {
                log.error("Error while sleeping: ", e);
                break;
            }
        }

    }
    public static void main(String[] args) {
        RetrofitClient retrofitClient = new RetrofitClient();
        GrpcServer grpcServer = new GrpcServer(Integer.parseInt(args[0]), retrofitClient);
        GrpcClient grpcClient = null;
        if (retrofitClient.getNearestNeighbor() != null) {
             grpcClient = new GrpcClient(args[1], retrofitClient.getNearestNeighbor().getPort());
        }

        try {
            grpcServer.start();
            retrofitClient.start(args[1], Integer.parseInt(args[0]));
            if (retrofitClient.getNearestNeighbor() != null) {
                grpcClient = new GrpcClient(args[1], retrofitClient.getNearestNeighbor().getPort());
            }
            sensorActive(grpcClient, retrofitClient);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}