package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.FoodSelectionDto;
import lk.purna.healthy_life.controller.request.FoodSelectionRq;
import lk.purna.healthy_life.controller.response.FoodResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.FoodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class FoodController {

    private ModelMapper modelMapper;
    private FoodService foodService;

    @PostMapping("/users/{user_id}/foods")
    public ResponseEntity<List<FoodResponse>> createFoodForUser(
            @PathVariable("user_id") Long userId,
            @RequestBody List<FoodSelectionRq> foodSelectionRqList)
            throws UserNotFoundException {

        // Print each FoodSelectionRequest's foodId to the console
        foodSelectionRqList.forEach(request -> {
            System.out.println("FoodSelectionRequest foodId: " + request.getFoodId());
            System.out.println("FoodSelectionRequest food type: " + request.getFoodType());
            System.out.println("FoodSelectionRequest amount: " + request.getAmountInGram());
        });

        // Map each FoodSelectionRequest to FoodSelectionDto
        List<FoodSelectionDto> foodSelectionDtoList = foodSelectionRqList.stream()
                .map(foodSelectionRq -> modelMapper.map(foodSelectionRq, FoodSelectionDto.class))
                .collect(Collectors.toList());

        // Call the service method
        List<FoodResponse> foodResponseList = foodService.createFoodForUser(userId, foodSelectionDtoList);

        // Return the response entity with the list of FoodResponse and HTTP status CREATED
        return new ResponseEntity<>(foodResponseList, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{user_id}/foods/{food_id}")
    public ResponseEntity<FoodResponse> deleteFoodForUser(
            @PathVariable("user_id") Long userId,
            @PathVariable("food_id") Long foodId) throws UserNotFoundException, FoodDetailsNotFoundException {

        FoodResponse foodResponse = foodService.deleteFoodForUser(userId, foodId);
        return new ResponseEntity<>(foodResponse,HttpStatus.ACCEPTED);
    }

}
