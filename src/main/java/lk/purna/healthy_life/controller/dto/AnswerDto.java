package lk.purna.healthy_life.controller.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.Gender;
import lombok.Data;

@Data
public class AnswerDto {

    private String goal;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private Float height;
    private Float weight;
    private String activityLevelType;
    private Float goalWeight;
    private String gymStatus;
}
