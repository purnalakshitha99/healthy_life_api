package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.request.SugarLevelRq;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.SugarLevelService;
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
public class SugarLevelController {

    private ModelMapper modelMapper;
    private SugarLevelService sugarLevelService;


    @PostMapping("/users/{user_id}/sugar_levels")
    public ResponseEntity<SugarLevelResponse> addSugarLevelByUser(@PathVariable("user_id")Long userId, @RequestBody SugarLevelRq sugarLevelRq)throws UserNotFoundException {

        SugarLevelDto sugarLevelDto = modelMapper.map(sugarLevelRq, SugarLevelDto.class);

        SugarLevelResponse sugarLevelResponse = sugarLevelService.addSugarLevelByUser(userId,sugarLevelDto);

        return ResponseEntity.created(URI.create("/sugar_levels")).body(sugarLevelResponse);

    }

    @GetMapping("/users/{user_id}/sugar_levels")
    public List<ResponseEntity<List<SugarLevelResponse>>> getSpecificUserSugarLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, SugarLevelNotFoundException{

        List<SugarLevelResponse> sugarLevelResponseList = sugarLevelService.getSpecificUserSugarLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(sugarLevelResponseList, HttpStatus.FOUND));
    }
}
