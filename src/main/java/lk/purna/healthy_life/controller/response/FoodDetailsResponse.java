package lk.purna.healthy_life.controller.response;

import lombok.Data;

@Data
public class FoodDetailsResponse {

    private Long id;
    private String name;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;
    private Float amount;
}
