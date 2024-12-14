package fer.hr.rassus.lab1;

import fer.hr.rassus.lab1.grpc.ReadingRequest;
import fer.hr.rassus.lab1.grpc.ReadingResponse;
import fer.hr.rassus.lab1.grpc.SensorGrpcServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcServer {
    private final Server server;
    private static RetrofitClient retrofitClient = null;

    public GrpcServer(int port, RetrofitClient retrofitClient) {
        this.server = ServerBuilder.forPort(port)
                .addService(new SensorGrpcService())
                .build();
        GrpcServer.retrofitClient = retrofitClient;
    }

    public void start() throws  IOException {
        server.start();
        log.info("GRPC SERVER STARTED ON PORT: {}", server.getPort());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("SHUTTING DOWN GRPC SERVER SINCE JVM IS SHUTTING DOWN");
            try {
                GrpcServer.this.stop();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            System.err.println("SERVER SHUT DOWN");
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private static class SensorGrpcService extends SensorGrpcServiceGrpc.SensorGrpcServiceImplBase {

        @Override
        public void getReading(ReadingRequest request, StreamObserver<ReadingResponse> responseObserver) {
            log.info("RECEIVED REQUEST FOR SENSOR WITH ID: " + request.getSensorId());
            long secondRunning = (System.currentTimeMillis() - retrofitClient.getStartTime()) / 1000;
            int row = (int) (secondRunning % 100) + 1;
            MeasurementDTO measurment = retrofitClient.readFromCSV(row);
           ReadingResponse response = ReadingResponse.newBuilder()
                   .setTemperature(measurment.getTemperature())
                   .setPressure(measurment.getPressure())
                   .setHumidity(measurment.getHumidity())
                   .setCo(measurment.getCo())
                   .setNo2(measurment.getNo2())
                   .setSo2(measurment.getSo2())
                        .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("RESPONDED WITH: {} {} {} {} {} {}" ,
                     response.getTemperature(), response.getPressure(), response.getHumidity(),
                    response.getCo(), response.getNo2(), response.getSo2());


        }


    }
}



