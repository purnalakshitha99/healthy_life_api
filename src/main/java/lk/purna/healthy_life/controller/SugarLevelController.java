package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.request.SugarLevelRq;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.SugarLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
