package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;

import java.util.List;

public interface FoodDetailsService {
    FoodDetailsResponse createFoodDetails(FoodDetailsDto foodDetailsDto);

   List<FoodDetailsResponse> getAllFoods();
}
