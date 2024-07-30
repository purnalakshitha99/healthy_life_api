package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.WaterLevelDto;
import lk.purna.healthy_life.controller.request.WaterLevelRq;
import lk.purna.healthy_life.controller.response.WaterLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WaterLevelNotFoundException;
import lk.purna.healthy_life.service.WaterLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class WaterLevelController {

    private ModelMapper modelMapper;
    private WaterLevelService waterLevelService;

    @PostMapping("/users/{user_id}/water_levels")
    public ResponseEntity<WaterLevelResponse> createWaterLevelForUser(@PathVariable("user_id")Long userId, @RequestBody WaterLevelRq waterLevelRq)throws UserNotFoundException {

        WaterLevelDto waterLevelDto = modelMapper.map(waterLevelRq, WaterLevelDto.class);
        WaterLevelResponse waterLevelResponse = waterLevelService.createWaterLevelForUser(userId,waterLevelDto);

        return new ResponseEntity<>(waterLevelResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}/water_levels")
    public List<ResponseEntity<List<WaterLevelResponse>>> getSpecificUserWaterLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, WaterLevelNotFoundException {

        List <WaterLevelResponse> waterLevelResponseList = waterLevelService.getSpecificUserWaterLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(waterLevelResponseList,HttpStatus.FOUND));
    }




}
