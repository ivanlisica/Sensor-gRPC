package fer.hr.rassus.lab1;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okio.Buffer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class RetrofitClient {
    private final ApiService apiService;
    private static final String BASE_URL = "http://localhost:8080";
    //private static final double EARTH_RADIUS = 6371.0;
    @Getter
    private Long sensorId;
    @Getter
    private SensorDTO nearestNeighbor;
    @Getter
    private long startTime;
    private GrpcClient grpcClient;
    private GrpcServer grpcServer;


    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(ApiService.class);
        //this.grpcClient = grpcClient;
       // this.grpcServer = grpcServer;
        //this.measurementIds = List.of();

    }
    public void register(double latitude, double longitude, String ip, int port) {
        SensorDTO sensorDTO = new SensorDTO(latitude, longitude, ip, port);
        startTime = System.currentTimeMillis();

        try {
            Response<Long> response = apiService.registerSensor(sensorDTO).execute();
            if (response.isSuccessful() && response.body() != null) {
                sensorId = response.body();
                log.info("Sensor successfully registred: {}", response.body().intValue());
            } else {
                log.error("Register failed. Status: {}", response.code());
                System.exit(1);
            }
        } catch (Exception e) {
            log.error("Error while registering sensor: " + e.getMessage());
            System.exit(1);
        }
    }
    public void findNearestNeighbor() {
        if (sensorId == null) {
            log.error("Sensor not registered");
            return;
        }
        try {
            Response<SensorDTO> response = apiService.getNearestNeighbor(sensorId).execute();
            if (response.isSuccessful() && response.body() != null) {
                nearestNeighbor = response.body();
                log.info("Nearest neighbor: {}", nearestNeighbor.toString());
            } else {
                log.error("Nearest neighbor was not found. Status: {}", response.code());
            }
        } catch (Exception e) {
            log.error("Error while finding nearest neighbor: " + e.getMessage());
        }

    }
    public void sendMeasurement(MeasurementDTO measurementDTO) {
        if (sensorId == null) {
            log.error("Sensor not registered");
            return;
        }
        log.info("Sending measurement: {}", measurementDTO);
        try {
            Response<Long> response = apiService.addMeasurement(sensorId, measurementDTO).execute();
            if (response.isSuccessful() && response.body() != null) {
                //this.measurementIds.add(response.body());
                log.info("Measurement successfully sent. ID measurement: {}", response.body().intValue());
            } else {
                log.error("Measurement unsuccessfully sent. Status: {}", response.code());
            }
        } catch (Exception e) {
            log.error("Error while sending measurement: " + e.getMessage());
        }
    }
    public MeasurementDTO readFromCSV(int rowNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ivan\\Desktop\\FER\\7. semestar\\Raspodijeljeni sustavi\\Lab1\\lab1\\klijent\\src\\main\\resources\\readings.csv"))) {
            String line;
            int currentRow = 0;

            br.readLine();

            while ((line = br.readLine()) != null && currentRow < rowNumber) {
                currentRow++;
            }
            if (line != null) {
                String[] values = line.split(",", -1);

                double temperature = (values.length > 0 && !values[0].isEmpty()) ? Double.parseDouble(values[0]) : 0;
                double pressure = (values.length > 1 && !values[1].isEmpty()) ? Double.parseDouble(values[1]) : 0;
                double humidity = (values.length > 2 && !values[2].isEmpty()) ? Double.parseDouble(values[2]) : 0;
                double co = (values.length > 3 && !values[3].isEmpty()) ? Double.parseDouble(values[3]) : 0;
                double no2 = (values.length > 4 && !values[4].isEmpty()) ? Double.parseDouble(values[4]) : 0;
                double so2 = (values.length > 5 && !values[5].isEmpty()) ? Double.parseDouble(values[5]) : 0;

                log.info("Reading from CSV: {} {} {} {} {} {}", temperature, pressure, humidity, co, no2, so2);


                return new MeasurementDTO(temperature, pressure, humidity, co, no2, so2);
            }
        } catch (IOException e) {
            log.error("Error while reading CSV: ", e);
        }
        return null;

    }
    private Double calculateAverage(Double val1, Double val2) {
        if (val1 == null && val2 == null) return 0.0;
       else if ((val1 == null || val1 == 0) && val2 != null) return val2;
        else if (val2 == null || val2 == 0) return val1;
        return (val1 + val2) / 2;
    }
    public MeasurementDTO calibrateMeasurement(MeasurementDTO own, MeasurementDTO neighbor) {
        if (neighbor == null) {
            return own;
        }
        MeasurementDTO measurementDTO = new MeasurementDTO(
                calculateAverage(own.getTemperature(), neighbor.getTemperature()),
                calculateAverage(own.getPressure(), neighbor.getPressure()),
                calculateAverage(own.getHumidity(), neighbor.getHumidity()),
                calculateAverage(own.getCo(), neighbor.getCo()),
                calculateAverage(own.getNo2(), neighbor.getNo2()),
                calculateAverage(own.getSo2(), neighbor.getSo2())
        );
        log.info("Calibrated measurement: {}", measurementDTO);
        return measurementDTO;
    }


        public void start(String ip, int port) {
            double latitude = 45.75 + (Math.random() * (45.85 - 45.75));
            double longitude = 15.87 + (Math.random() * (16.0 - 15.87));
            register(latitude, longitude, ip, port);
            findNearestNeighbor();
            //setSensor();
            log.info("Sensor {} started", sensorId);

        }

}
