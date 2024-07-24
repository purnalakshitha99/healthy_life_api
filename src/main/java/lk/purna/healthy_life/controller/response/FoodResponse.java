package lk.purna.healthy_life.controller.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.FoodType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FoodResponse {

    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private Long id;
    private String name;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;
    private Float amount;
}
