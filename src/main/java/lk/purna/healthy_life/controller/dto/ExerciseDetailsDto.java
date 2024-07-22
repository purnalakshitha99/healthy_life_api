package lk.purna.healthy_life.controller.dto;

import lk.purna.healthy_life.model.TimeType;
import lombok.Data;

@Data
public class ExerciseDetailsDto {

    private String name;
    private float burnCalories;
    private TimeType timeType;
    private Integer timeAmount;

}
