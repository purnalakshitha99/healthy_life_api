package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.response.CalculationResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;

public interface CalculationService {
    CalculationResponse calculateAdjustedCalories(Long userId)throws UserNotFoundException;
}
