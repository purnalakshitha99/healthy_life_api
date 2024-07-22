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

@Service
@AllArgsConstructor
public class ExerciseDetailsServiceImpl implements ExerciseDetailsService {

    private ModelMapper modelMapper;
    private final ExerciseDetailsRepository exerciseDetailsRepository;

    @Override
    public ExerciseDetailsResponse createExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {

        ExerciseDetails exerciseDetails = modelMapper.map(exerciseDetailsDto,ExerciseDetails.class);
        System.out.println("puuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"+exerciseDetails.getTimeType());
//        exerciseDetails.setTimeType(exerciseDetailsDto.getTimeType());
        exerciseDetailsRepository.save(exerciseDetails);
        System.out.println("ssssssssssssssssssssssssssssssssss"+exerciseDetails.getTimeType());

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
}
