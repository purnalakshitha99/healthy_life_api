package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.request.ExerciseDetailsRq;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.service.ExerciseDetailsService;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ExerciseDetailsController {

    private final ExerciseDetailsService exerciseDetailsService;
    private final ModelMapper modelMapper;

    @PostMapping("/exercise_details")
    public ResponseEntity<ExerciseDetailsResponse> createExerciseDetails(@RequestBody ExerciseDetailsRq exerciseDetailsRq){


        ExerciseDetailsDto exerciseDetailsDto = modelMapper.map(exerciseDetailsRq,ExerciseDetailsDto.class);

        ExerciseDetailsResponse exerciseDetailsResponse = exerciseDetailsService.createExerciseDetails(exerciseDetailsDto);

        return  ResponseEntity.created(URI.create("/exercise_details")).body(exerciseDetailsResponse);
    }

    @GetMapping("/exercise_details/{exercise_details_id}")
    public ResponseEntity<ExerciseDetailsResponse> getSpecificExerciseDetails(@PathVariable("exercise_details_id")Long exerciseDetailsId)throws ExerciseDetailsNotFoundException{

        ExerciseDetailsResponse exerciseDetailsResponse = exerciseDetailsService.getSpecificExerciseDetails(exerciseDetailsId);

        return new ResponseEntity<>(exerciseDetailsResponse, HttpStatus.FOUND);
    }

    @PutMapping("/exercise_details/{exercise_details_id}")
    public ResponseEntity<ExerciseDetailsResponse> updateSpecificExerciseDetails(@PathVariable("exercise_details_id")Long exerciseDetailsId,@RequestBody ExerciseDetailsRq exerciseDetailsRq)throws ExerciseDetailsNotFoundException{

        ExerciseDetailsDto exerciseDetailsDto = modelMapper.map(exerciseDetailsRq,ExerciseDetailsDto.class);
        ExerciseDetailsResponse exerciseDetailsResponse = exerciseDetailsService.updateSpecificExerciseDetails(exerciseDetailsId,exerciseDetailsDto);

        return new ResponseEntity<>(exerciseDetailsResponse,HttpStatus.CREATED);
    }

    @GetMapping("/exercise_details")
    public List<ResponseEntity<ExerciseDetailsResponse>> getAllExerciseDetails() throws ExerciseDetailsNotFoundException {
        List<ExerciseDetailsResponse> exerciseDetailsResponseList = exerciseDetailsService.getAllExerciseDetails();

        return exerciseDetailsResponseList.stream()
                .map(exerciseDetailsResponse -> new ResponseEntity<>(
                        modelMapper.map(exerciseDetailsResponse, ExerciseDetailsResponse.class),
                        HttpStatus.FOUND))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/exercise_details/{exercise_details_id}")
    public ResponseEntity<ExerciseDetailsResponse>  deleteSpecificExercise(@PathVariable("exercise_details_id")Long exerciseDetailsId)throws ExerciseDetailsNotFoundException{

        ExerciseDetailsResponse exerciseDetailsResponse = exerciseDetailsService.deleteSpecificExercise(exerciseDetailsId);

        return new ResponseEntity<>(exerciseDetailsResponse,HttpStatus.FOUND);
    }




}
