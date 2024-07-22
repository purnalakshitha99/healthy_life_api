package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;

import java.util.List;

public interface ExerciseDetailsService {
    ExerciseDetailsResponse createExerciseDetails(ExerciseDetailsDto exerciseDetailsDto);

    ExerciseDetailsResponse getSpecificExerciseDetails(Long exerciseDetailsId)throws ExerciseDetailsNotFoundException;

    ExerciseDetailsResponse updateSpecificExerciseDetails(Long exerciseDetailsId, ExerciseDetailsDto exerciseDetailsDto)throws ExerciseDetailsNotFoundException;

    List<ExerciseDetailsResponse> getAllExerciseDetails()throws ExerciseDetailsNotFoundException;
}
