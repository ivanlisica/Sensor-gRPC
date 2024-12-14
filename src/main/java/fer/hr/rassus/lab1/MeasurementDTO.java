package fer.hr.rassus.lab1;


import lombok.Data;

@Data

public class MeasurementDTO {
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double no2;
    private Double so2;

    public MeasurementDTO(Double temperature, Double pressure, Double humidity, Double co, Double so2, Double no2) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.co = co;
        this.so2 = so2;
        this.no2 = no2;
    }

    public MeasurementDTO() {

    }
}
