package lk.purna.healthy_life.controller.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SugarLevelResponse {

    private Long id;
    private Float newSugarLevel;
    private LocalDate date;
    private LocalTime time;

    private Long userId;
}
