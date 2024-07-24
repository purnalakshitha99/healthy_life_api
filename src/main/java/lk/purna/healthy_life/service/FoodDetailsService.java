package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.model.FoodDetails;

import java.util.List;

public interface FoodDetailsService {
    FoodDetailsResponse createFoodDetails(FoodDetailsDto foodDetailsDto);

   List<FoodDetailsResponse> getAllFoods()throws FoodDetailsNotFoundException;

    FoodDetailsResponse updateFoodDetails(Long foodDetailsId, FoodDetailsDto foodDetailsDto)throws FoodDetailsNotFoundException;

    FoodDetailsResponse getSpecificFoodDetails(Long foodDetailsId)throws FoodDetailsNotFoundException;

    FoodDetailsResponse deleteSpecificFoodDetails(Long foodDetailsId)throws FoodDetailsNotFoundException;


    List<FoodDetails> searchFoodByPrefix(String prefix);
}
