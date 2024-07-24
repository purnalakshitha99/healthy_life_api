package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.FoodSelectionDto;
import lk.purna.healthy_life.controller.request.FoodSelectionRq;
import lk.purna.healthy_life.controller.response.FoodResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.util.List;

public interface FoodService {
    List<FoodResponse> createFoodForUser(Long userId, List<FoodSelectionDto> foodSelectionDtoList)throws UserNotFoundException, FoodDetailsNotFoundException;
}
