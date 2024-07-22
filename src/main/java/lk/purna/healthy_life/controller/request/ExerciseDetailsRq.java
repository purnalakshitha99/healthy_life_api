package lk.purna.healthy_life.controller.request;

import lk.purna.healthy_life.model.TimeType;
import lombok.Data;

@Data
public class ExerciseDetailsRq {

    private String name;
    private float burnCalories;
    private TimeType timeType;
    private Integer timeAmount;
}
