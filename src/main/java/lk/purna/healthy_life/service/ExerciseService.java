package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.dto.ExerciseDto;
import lk.purna.healthy_life.controller.response.ExerciseResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

public interface ExerciseService {
    ExerciseResponse createExerciseForUser(Long userId, ExerciseDto exerciseDto)throws UserNotFoundException, ExerciseDetailsNotFoundException;
}
