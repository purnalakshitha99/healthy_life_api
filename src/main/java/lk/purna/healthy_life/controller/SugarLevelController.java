package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.request.SugarLevelRq;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.SugarLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class SugarLevelController {

    private ModelMapper modelMapper;
    private SugarLevelService sugarLevelService;


    @PostMapping("/users/{user_id}/sugar_levels")
    public ResponseEntity<SugarLevelResponse> addSugarLevelByUser(@PathVariable("user_id")Long userId, @RequestBody SugarLevelRq sugarLevelRq)throws UserNotFoundException,DateNotFoundException {

        SugarLevelDto sugarLevelDto = modelMapper.map(sugarLevelRq, SugarLevelDto.class);

        SugarLevelResponse sugarLevelResponse = sugarLevelService.addSugarLevelByUser(userId,sugarLevelDto);

        return ResponseEntity.created(URI.create("/sugar_levels")).body(sugarLevelResponse);

    }

    @GetMapping("/users/{user_id}/sugar_levels")
    public List<ResponseEntity<List<SugarLevelResponse>>> getSpecificUserSugarLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, SugarLevelNotFoundException{

        List<SugarLevelResponse> sugarLevelResponseList = sugarLevelService.getSpecificUserSugarLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(sugarLevelResponseList, HttpStatus.FOUND));
    }

    @GetMapping("/users/{user_id}/sugar_levels/date")
    public ResponseEntity<SugarLevelResponse> getUserSugarLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, SugarLevelNotFoundException {

        SugarLevelResponse sugarLevelResponse = sugarLevelService.getUserSugarLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(sugarLevelResponse,HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{user_id}/sugar_levels/date")
    public ResponseEntity<SugarLevelResponse> DeleteUserSugarLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, SugarLevelNotFoundException {

        SugarLevelResponse sugarLevelResponse = sugarLevelService.DeleteUserSugarLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(sugarLevelResponse,HttpStatus.ACCEPTED);
    }

    @PutMapping("/users/{user_id}/sugar_levels/date")
    public ResponseEntity<SugarLevelResponse> UpdateUserSugarLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date,@RequestBody SugarLevelRq sugarLevelRq)throws UserNotFoundException, SugarLevelNotFoundException {

        SugarLevelDto sugarLevelDto = modelMapper.map(sugarLevelRq,SugarLevelDto.class);
        SugarLevelResponse sugarLevelResponse = sugarLevelService.UpdateUserSugarLevelBySpecificDate(userId,date,sugarLevelDto);

        return new ResponseEntity<>(sugarLevelResponse,HttpStatus.ACCEPTED);
    }
}
