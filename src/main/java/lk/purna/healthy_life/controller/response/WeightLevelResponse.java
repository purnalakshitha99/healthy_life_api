package lk.purna.healthy_life.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeightLevelResponse {

    private Long weightLevelId;
    private LocalDate date;
    private Float newWeightLevel;
}
