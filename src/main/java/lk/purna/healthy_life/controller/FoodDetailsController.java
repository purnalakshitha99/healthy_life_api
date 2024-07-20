package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.request.FoodDetailsRq;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
@AllArgsConstructor
public class FoodDetailsController {

    private final ModelMapper modelMapper;
    private FoodDetailsService foodDetailsService;

    @PostMapping("/food_details")
    public ResponseEntity<FoodDetailsResponse> createFoodDetails(@RequestBody FoodDetailsRq foodDetailsRq) {

        FoodDetailsDto foodDetailsDto = modelMapper.map(foodDetailsRq, FoodDetailsDto.class);
        FoodDetailsResponse foodDetailsResponse = foodDetailsService.createFoodDetails(foodDetailsDto);

//        return new ResponseEntity<>(foodDetailsResponse, HttpStatus.CREATED);

        return ResponseEntity.created(URI.create("/food_details")).body(foodDetailsResponse);
    }
}
