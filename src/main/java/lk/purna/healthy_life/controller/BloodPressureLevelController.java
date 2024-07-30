package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.BloodPressureLevelDto;
import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.request.BloodPressureLevelRq;
import lk.purna.healthy_life.controller.request.CholesterolLevelRq;
import lk.purna.healthy_life.controller.response.BloodPressureLevelResponse;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.exception.BloodPressureLevelNotFoundException;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.BloodPressureLevelService;
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
public class BloodPressureLevelController {

    private ModelMapper modelMapper;
    private final BloodPressureLevelService bloodPressureLevelService;

    @PostMapping("/users/{user_id}/blood_pressure_levels")
    public ResponseEntity<BloodPressureLevelResponse> addBloodPressureForUser(@PathVariable("user_id")Long userId, @RequestBody BloodPressureLevelRq bloodPressureLevelRq)throws UserNotFoundException, DateNotFoundException{

        BloodPressureLevelDto bloodPressureLevelDto = modelMapper.map(bloodPressureLevelRq, BloodPressureLevelDto.class);
        BloodPressureLevelResponse bloodPressureLevelResponse = bloodPressureLevelService.addBloodPressureForUser(userId,bloodPressureLevelDto);

        return ResponseEntity.created(URI.create("/blood_pressure_levels")).body(bloodPressureLevelResponse);
    }

    @GetMapping("/users/{user_id}/blood_pressure_levels")
    public List<ResponseEntity<List<BloodPressureLevelResponse>>> getSpecificUserBloodPressureLevels(@PathVariable("user_id")Long userId)throws UserNotFoundException, BloodPressureLevelNotFoundException {

        List<BloodPressureLevelResponse> bloodPressureLevelResponseList = bloodPressureLevelService.getSpecificUserBloodPressureLevels(userId);

        return Collections.singletonList(new ResponseEntity<>(bloodPressureLevelResponseList, HttpStatus.FOUND));
    }

    @GetMapping("/users/{user_id}/blood_pressure_levels/date")
    public ResponseEntity<BloodPressureLevelResponse> getUserBloodPressureLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, BloodPressureLevelNotFoundException {

        BloodPressureLevelResponse bloodPressureLevelResponse = bloodPressureLevelService.getUserBloodPressureLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(bloodPressureLevelResponse,HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{user_id}/blood_pressure_levels/date")
    public ResponseEntity<BloodPressureLevelResponse> DeleteUserBloodPressureLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date)throws UserNotFoundException, BloodPressureLevelNotFoundException {

        BloodPressureLevelResponse bloodPressureLevelResponse = bloodPressureLevelService.DeleteUserBloodPressureLevelBySpecificDate(userId,date);

        return new ResponseEntity<>(bloodPressureLevelResponse,HttpStatus.ACCEPTED);
    }


    @PutMapping("/users/{user_id}/blood_pressure_levels/date")
    public ResponseEntity<BloodPressureLevelResponse> UpdateUserBloodPressureLevelBySpecificDate(@PathVariable("user_id")Long userId, @RequestParam LocalDate date,@RequestBody BloodPressureLevelRq bloodPressureLevelRq)throws UserNotFoundException,BloodPressureLevelNotFoundException{

        BloodPressureLevelDto bloodPressureLevelDto = modelMapper.map(bloodPressureLevelRq,BloodPressureLevelDto.class);
        BloodPressureLevelResponse bloodPressureLevelResponse = bloodPressureLevelService.UpdateUserBloodPressureLevelBySpecificDate(userId,date,bloodPressureLevelDto);

        return new ResponseEntity<>(bloodPressureLevelResponse,HttpStatus.ACCEPTED);

    }
//
//
//    @DeleteMapping("/users/cholesterol_levels")
//    public void deleteAll(){
//
//        cholesterolLevelRepository.deleteAll();
//    }
}
