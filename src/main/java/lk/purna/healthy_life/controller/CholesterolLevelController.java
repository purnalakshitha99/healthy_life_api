package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.request.CholesterolLevelRq;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.repository.CholesterolLevelRepository;
import lk.purna.healthy_life.service.CholesterolLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class CholesterolLevelController {

    private final CholesterolLevelRepository cholesterolLevelRepository;
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

    @GetMapping("/users/{user_id}/cholesterol_levels/date")
    public ResponseEntity<CholesterolLevelResponse> getUserCholesterolLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, CholesterolLevelNotFoundException {

        CholesterolLevelResponse cholesterolLevelResponse = cholesterolLevelService.getUserCholesterolLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(cholesterolLevelResponse,HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{user_id}/cholesterol_levels/date")
    public ResponseEntity<CholesterolLevelResponse> DeleteUserCholesterolLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, CholesterolLevelNotFoundException {

        CholesterolLevelResponse cholesterolLevelResponse = cholesterolLevelService.DeleteUserCholesterolLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(cholesterolLevelResponse,HttpStatus.ACCEPTED);
    }


    @PutMapping("/users/{user_id}/cholesterol_levels/date")
    public ResponseEntity<CholesterolLevelResponse> UpdateUserCholesterolLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date,@RequestBody CholesterolLevelRq cholesterolLevelRq)throws UserNotFoundException,CholesterolLevelNotFoundException{

        CholesterolLevelDto cholesterolLevelDto = modelMapper.map(cholesterolLevelRq,CholesterolLevelDto.class);
        CholesterolLevelResponse cholesterolLevelResponse = cholesterolLevelService.UpdateUserCholesterolLevelBySpecificDate(userId,date,cholesterolLevelDto);

        return new ResponseEntity<>(cholesterolLevelResponse,HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/users/cholesterol_levels")
    public void deleteAll(){

        cholesterolLevelRepository.deleteAll();
    }
}
