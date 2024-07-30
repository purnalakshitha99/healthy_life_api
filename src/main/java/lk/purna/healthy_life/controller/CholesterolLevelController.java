package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.request.CholesterolLevelRq;
import lk.purna.healthy_life.controller.request.SugarLevelRq;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.CholesterolLevelService;
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
public class CholesterolLevelController {

    private ModelMapper modelMapper;
    private CholesterolLevelService cholesterolLevelService;

    @PostMapping("/users/{user_id}/cholesterol_levels")
    public ResponseEntity<CholesterolLevelResponse> addCholesterolLevelsByUser(@PathVariable("user_id")Long userId, @RequestBody CholesterolLevelRq cholesterolLevelRq)throws UserNotFoundException, DateNotFoundException {

        CholesterolLevelDto cholesterolLevelDto = modelMapper.map(cholesterolLevelRq, CholesterolLevelDto.class);

        CholesterolLevelResponse cholesterolLevelResponse = cholesterolLevelService.addCholesterolLevelsByUser(userId,cholesterolLevelDto);

        return ResponseEntity.created(URI.create("/cholesterol_levels")).body(cholesterolLevelResponse);

    }

    @GetMapping("/users/{user_id}/cholesterol_levels")
    public List<ResponseEntity<List<CholesterolLevelResponse>>> getSpecificUserCholesterolLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, CholesterolLevelNotFoundException {

        List<CholesterolLevelResponse> cholesterolLevelResponseList = cholesterolLevelService.getSpecificUserCholesterolLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(cholesterolLevelResponseList, HttpStatus.FOUND));
    }
}
