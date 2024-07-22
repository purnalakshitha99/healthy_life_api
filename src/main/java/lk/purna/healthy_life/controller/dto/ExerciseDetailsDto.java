package lk.purna.healthy_life.controller.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.TimeType;
import lombok.Data;

@Data
public class ExerciseDetailsDto {

    private String name;
    private float burnCalories;
    @Enumerated(EnumType.STRING)
    private TimeType timeType;
    private Integer timeAmount;

}
