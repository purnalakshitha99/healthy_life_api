package lk.purna.healthy_life.controller.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.TimeType;
import lombok.Data;

@Data
public class ExerciseDto {

    private Long exerciseId;
    @Enumerated(EnumType.STRING)
    private TimeType timeType;
    private Integer timeAmount;
}
