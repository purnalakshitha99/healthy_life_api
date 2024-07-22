package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.model.ExerciseDetails;
import lk.purna.healthy_life.repository.ExerciseDetailsRepository;
import lk.purna.healthy_life.service.ExerciseDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseDetailsServiceImpl implements ExerciseDetailsService {

    private ModelMapper modelMapper;
    private final ExerciseDetailsRepository exerciseDetailsRepository;

    @Override
    public ExerciseDetailsResponse createExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {

        ExerciseDetails exerciseDetails = modelMapper.map(exerciseDetailsDto,ExerciseDetails.class);

        exerciseDetailsRepository.save(exerciseDetails);


        return modelMapper.map(exerciseDetails,ExerciseDetailsResponse.class);
    }

    @Override
    public ExerciseDetailsResponse getSpecificExerciseDetails(Long exerciseDetailsId) throws ExerciseDetailsNotFoundException {


        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDetailsId).orElseThrow(
                ()-> new ExerciseDetailsNotFoundException("That Exercise Details Not in a Database")
        );

        return modelMapper.map(exerciseDetails,ExerciseDetailsResponse.class);
    }

    @Override
    public ExerciseDetailsResponse updateSpecificExerciseDetails(Long exerciseDetailsId, ExerciseDetailsDto exerciseDetailsDto) throws ExerciseDetailsNotFoundException {

        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDetailsId).orElseThrow(
                ()-> new ExerciseDetailsNotFoundException("That Exercise Details Not in a Database")
        );

        modelMapper.map(exerciseDetailsDto,exerciseDetails);

        exerciseDetailsRepository.save(exerciseDetails);

        return modelMapper.map(exerciseDetails,ExerciseDetailsResponse.class);
    }

    public List<ExerciseDetailsResponse> getAllExerciseDetails()throws ExerciseDetailsNotFoundException{

        List<ExerciseDetails> exerciseDetailsList = exerciseDetailsRepository.findAll();



        if (exerciseDetailsList.isEmpty()){
            throw new ExerciseDetailsNotFoundException("Exercise not in a DB, DB is empty");
        }

      return exerciseDetailsList.stream().map(exerciseDetails -> modelMapper.map(exerciseDetails,ExerciseDetailsResponse.class)).toList();


    }
}
