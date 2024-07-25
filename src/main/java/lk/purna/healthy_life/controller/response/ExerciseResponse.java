package lk.purna.healthy_life.controller.response;

import lk.purna.healthy_life.model.TimeType;
import lombok.Data;

@Data
public class ExerciseResponse {

    private String name;
    private float burnCalories;
    private TimeType timeType;
    private Integer timeAmount;
}
