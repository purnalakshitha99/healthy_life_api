package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.request.ExerciseDetailsRq;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ExerciseDetailsController {

    private final FoodDetailsService foodDetailsService;
    private final ModelMapper modelMapper;

    @PostMapping("/exercise_details")
    public ResponseEntity<ExerciseDetailsResponse> createExerciseDetails(@RequestBody ExerciseDetailsRq exerciseDetailsRq){

        ExerciseDetailsDto exerciseDetailsDto = modelMapper.map(exerciseDetailsRq,ExerciseDetailsDto.class);
        ExerciseDetailsResponse exerciseDetailsResponse = foodDetailsService.createExerciseDetails(exerciseDetailsDto);

        return  ResponseEntity.created(URI.create("/exercise_details")).body(exerciseDetailsResponse);
    }
}
