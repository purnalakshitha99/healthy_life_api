package lk.purna.healthy_life.controller.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.FoodType;
import lombok.Data;


@Data
public class FoodSelectionRq {

    private Long foodId;
    private Float amountInGram;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
}
