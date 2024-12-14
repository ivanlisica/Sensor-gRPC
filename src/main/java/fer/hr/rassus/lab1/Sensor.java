package fer.hr.rassus.lab1;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class Sensor {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;
}
