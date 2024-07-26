package lk.purna.healthy_life.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.Gender;
import lombok.Data;

@Data
public class AnswerResponse {

    private String goal;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private Float height;
    private Float weight;
    private String activityLevel;
    private Float goalWeight;
    private String gymStatus;
}
