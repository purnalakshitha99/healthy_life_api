package lk.purna.healthy_life.controller.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WaterLevelResponse {

    private Long id;
    private Float litters;
    private LocalDate date;
    private LocalTime time;
}
