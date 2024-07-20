package lk.purna.healthy_life.controller.dto;

import lombok.Data;

@Data
public class FoodDetailsDto {

    private String name;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;
    private Float amount;

}
