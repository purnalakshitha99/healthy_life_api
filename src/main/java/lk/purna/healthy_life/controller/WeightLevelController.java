package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.WeightLevelDto;
import lk.purna.healthy_life.controller.request.WeightLevelRq;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.controller.response.WeightLevelResponse;
import lk.purna.healthy_life.exception.DateException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WeightLevelNotFoundException;
import lk.purna.healthy_life.model.WeightLevel;
import lk.purna.healthy_life.service.WeightLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class WeightLevelController {

    private ModelMapper modelMapper;
    private WeightLevelService weightLevelService;

    @PostMapping("/users/{user_id}/weight_levels")
    public ResponseEntity<WeightLevelResponse> createWeightForUser(@PathVariable("user_id")Long userId, @RequestBody WeightLevelRq weightLevelRq)throws UserNotFoundException, DateException{

        WeightLevelDto weightLevelDto = modelMapper.map(weightLevelRq,WeightLevelDto.class);
        WeightLevelResponse weightLevelResponse = weightLevelService.createWeightForUser(userId,weightLevelDto);

        return new ResponseEntity<>(weightLevelResponse, HttpStatus.CREATED);
    }


    @GetMapping("/users/{user_id}/weight_levels/date")
    public ResponseEntity<WeightLevelResponse> getSpecificDateWeightLevel(@PathVariable("user_id")Long userId,@RequestParam LocalDate date)throws DateNotFoundException,UserNotFoundException{

        System.out.println("halooo");
        WeightLevelResponse weightLevelResponse = weightLevelService.getSpecificDateWeightLevel(userId,date);

        return new ResponseEntity<>(weightLevelResponse,HttpStatus.FOUND);

    }

    @GetMapping("/users/{user_id}/weight_levels")
    public List<ResponseEntity<List<WeightLevelResponse>>> getSpecificUserWeightLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, WeightLevelNotFoundException {

        List<WeightLevelResponse> weightLevelResponselist = weightLevelService.getSpecificUserWeightLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(weightLevelResponselist, HttpStatus.FOUND));
    }

    @GetMapping("/users/{user_id}/latest_weights")
    public Float getLatestWeightByUserId(@PathVariable("user_id")Long userId)throws UserNotFoundException,WeightLevelNotFoundException{

        return weightLevelService.getLatestWeightByUserId(userId);
    }
}
