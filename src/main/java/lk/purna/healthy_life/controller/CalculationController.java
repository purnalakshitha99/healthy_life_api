package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.response.CalculationResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CalculationController {

    private CalculationService calculationService;


    @GetMapping("/users/{user_id}/calculation")
    public CalculationResponse calculateAdjustedCalories(@PathVariable("user_id")Long userId)throws UserNotFoundException {
        System.out.println("helooooooooooooooooooooooooooooooooooooo");
      return calculationService.calculateAdjustedCalories(userId);
    }
}
