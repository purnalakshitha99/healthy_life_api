package lk.purna.healthy_life.controller;


import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.dto.ExerciseDto;
import lk.purna.healthy_life.controller.request.ExerciseRq;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.controller.response.ExerciseResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExerciseController {

    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;

    @PostMapping("/users/{user_id}/exercises")
    public ResponseEntity<ExerciseResponse> createExerciseForUser(@PathVariable("user_id")Long userId, @RequestBody ExerciseRq exerciseRq)throws UserNotFoundException, ExerciseDetailsNotFoundException {
        System.out.println("usr id   : "+userId);
        System.out.println("exercise id   : "+exerciseRq.getExerciseId());

        ExerciseDto exerciseDto = modelMapper.map(exerciseRq,ExerciseDto.class);

        ExerciseResponse exerciseResponse = exerciseService.createExerciseForUser(userId,exerciseDto);

        return new ResponseEntity<>(exerciseResponse,HttpStatus.CREATED);
    }
}
