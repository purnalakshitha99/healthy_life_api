package lk.purna.healthy_life.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.request.FoodDetailsRq;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.model.FoodDetails;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@AllArgsConstructor
public class FoodDetailsController {

    private final ModelMapper modelMapper;
    private FoodDetailsService foodDetailsService;

    @RolesAllowed("ADMIN")
    @PostMapping("/food_details")
    public ResponseEntity<FoodDetailsResponse> createFoodDetails(@RequestBody FoodDetailsRq foodDetailsRq) {

        FoodDetailsDto foodDetailsDto = modelMapper.map(foodDetailsRq, FoodDetailsDto.class);
        FoodDetailsResponse foodDetailsResponse = foodDetailsService.createFoodDetails(foodDetailsDto);

//        return new ResponseEntity<>(foodDetailsResponse, HttpStatus.CREATED);

        return ResponseEntity.created(URI.create("/food_details")).body(foodDetailsResponse);
    }

    @RolesAllowed({"ADMIN","USER"})
    @GetMapping("/food_details")
    public List<ResponseEntity<List<FoodDetailsResponse>>> getAllFoods()throws FoodDetailsNotFoundException {


      List<FoodDetailsResponse> foodDetailsResponseList = foodDetailsService.getAllFoods();

      return Collections.singletonList(new ResponseEntity<>(foodDetailsResponseList, HttpStatus.FOUND));
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/food_details/{food_details_id}")
    public ResponseEntity<FoodDetailsResponse> updateFoodDetails(@PathVariable("food_details_id")Long foodDetailsId, @RequestBody FoodDetailsRq foodDetailsRq)throws FoodDetailsNotFoundException{

        FoodDetailsDto foodDetailsDto = modelMapper.map(foodDetailsRq,FoodDetailsDto.class);
        FoodDetailsResponse foodDetailsResponse = foodDetailsService.updateFoodDetails(foodDetailsId,foodDetailsDto);

        return new ResponseEntity<>(foodDetailsResponse,HttpStatus.CREATED);
    }

    @RolesAllowed({"ADMIN","USER"})
    @GetMapping("/food_details/{food_details_id}")
    public ResponseEntity<FoodDetailsResponse> getSpecificFoodDetails(@PathVariable("food_details_id")Long foodDetailsId)throws FoodDetailsNotFoundException{

        FoodDetailsResponse foodDetailsResponse = foodDetailsService.getSpecificFoodDetails(foodDetailsId);

        return new ResponseEntity<>(foodDetailsResponse,HttpStatus.FOUND);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/food_details/{food_details_id}")
    public ResponseEntity<FoodDetailsResponse> deleteSpecificFoodDetails(@PathVariable("food_details_id")Long foodDetailsId)throws FoodDetailsNotFoundException{

        FoodDetailsResponse foodDetailsResponse = foodDetailsService.deleteSpecificFoodDetails(foodDetailsId);

        return new ResponseEntity<>(foodDetailsResponse,HttpStatus.FOUND);
    }

    @RolesAllowed({"ADMIN","USER"})
    @GetMapping("/foods/search")
    public List<FoodDetails> searchFoodDetails(@RequestParam String query){
        String prefix = query.length() > 3 ? query.substring(0,3) : query;
        return foodDetailsService.searchFoodByPrefix(prefix);
    }


}
