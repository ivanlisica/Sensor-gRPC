package fer.hr.rassus.lab1;

import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("/api")
    Call<Long> registerSensor(@Body SensorDTO sensorDTO);
    @GET("/api/sensor/{id}")
    Call<SensorDTO> getSensor(@Path("id") Long id);
    @GET("/api/neighbor/{id}")
    Call<SensorDTO> getNearestNeighbor(@Path("id") Long id);
    @POST("/api/sensor/{id}/measurement")
    Call<Long> addMeasurement(@Path("id") Long id, @Body MeasurementDTO measurementDTO);
    @GET("/api/sensor/measurement/{id}")
    Call<MeasurementDTO> getMeasurement(@Path("id") Long id);
    @GET("api/sensor/all")
    Call<List<Sensor>> getAllSensors();
    @GET("api/sensor/{id}/measurement/all")
    Call<List<Measurement>> getAllMeasurements(@Path("id") Long id);
}
