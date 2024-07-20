package lk.purna.healthy_life.controller.request;

import lombok.Data;

@Data
public class FoodDetailsRq {

    private String name;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;
    private Float amount;
}
