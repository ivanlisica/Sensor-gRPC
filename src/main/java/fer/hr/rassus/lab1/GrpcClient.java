package fer.hr.rassus.lab1;

import fer.hr.rassus.lab1.grpc.ReadingRequest;
import fer.hr.rassus.lab1.grpc.ReadingResponse;
import fer.hr.rassus.lab1.grpc.SensorGrpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class GrpcClient {
    private final ManagedChannel channel;
    private final SensorGrpcServiceGrpc.SensorGrpcServiceStub asyncStub;

    public GrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.asyncStub = SensorGrpcServiceGrpc.newStub(channel);
    }
    public MeasurementDTO getNeighbourMeasurement(Long sensorId) {
        try {
            ReadingRequest request = ReadingRequest.newBuilder().setSensorId(sensorId).build();
            log.info("SENDING REQUEST FOR SENSOR WITH ID: " + sensorId);
            CompletableFuture<MeasurementDTO> future = new CompletableFuture<>();
            MeasurementDTO measurementDTO = new MeasurementDTO();
            asyncStub.getReading(request, new StreamObserver<>() {
                @Override
                public void onNext(ReadingResponse value) {
                    measurementDTO.setTemperature(value.getTemperature());
                    measurementDTO.setPressure(value.getPressure());
                    measurementDTO.setHumidity(value.getHumidity());
                    measurementDTO.setCo(value.getCo());
                    measurementDTO.setSo2(value.getSo2());
                    measurementDTO.setNo2(value.getNo2());
                    log.info("RECEIVED RESPONSE: " + measurementDTO);
                    future.complete(measurementDTO);
                }

                @Override
                public void onError(Throwable t) {
                    log.error("ERROR WHILE RECEIVING RESPONSE: " + t.getMessage());
                    future.complete(null);
                }

                @Override
                public void onCompleted() {
                    log.info("RECEIVED RESPONSE COMPLETED");
                }
            });
            return measurementDTO;

        } catch (Exception e) {
            log.error("ERROR WHILE SENDING REQUEST: " + e.getMessage());
        }
        return null;
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

}
