package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.model.ExerciseDetails;
import lk.purna.healthy_life.model.FoodDetails;
import lk.purna.healthy_life.repository.ExerciseDetailsRepository;
import lk.purna.healthy_life.repository.FoodDetailsRepository;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodDetailsServiceImpl implements FoodDetailsService {

    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private ModelMapper modelMapper;
    private FoodDetailsRepository foodDetailsRepository;

    @Override
    public FoodDetailsResponse createFoodDetails(FoodDetailsDto foodDetailsDto) {

        System.out.println("food name"+foodDetailsDto.getName());
        FoodDetails foodDetails = modelMapper.map(foodDetailsDto, FoodDetails.class);

        foodDetailsRepository.save(foodDetails);

        return modelMapper.map(foodDetails,FoodDetailsResponse.class);
    }

    @Override
    public List<FoodDetailsResponse> getAllFoods()throws FoodDetailsNotFoundException {

       List<FoodDetails> foodDetailsList = foodDetailsRepository.findAll();

       if (foodDetailsList.isEmpty()){
           throw new FoodDetailsNotFoundException("food details is empty not food details in db");
       }

    return foodDetailsList.stream().map(foodDetails -> modelMapper.map(foodDetails,FoodDetailsResponse.class)).collect(Collectors.toList());
    }

    @Override
    public FoodDetailsResponse updateFoodDetails(Long foodDetailsId, FoodDetailsDto foodDetailsDto)throws FoodDetailsNotFoundException {

        FoodDetails foodDetails = foodDetailsRepository.findById(foodDetailsId).orElseThrow(
                ()-> new FoodDetailsNotFoundException("that food details is not in a database")
        );

        modelMapper.map(foodDetailsDto,foodDetails);

        foodDetailsRepository.save(foodDetails);

        return modelMapper.map(foodDetails,FoodDetailsResponse.class);

    }

    public FoodDetailsResponse getSpecificFoodDetails(Long foodDetailsId)throws FoodDetailsNotFoundException{

        FoodDetails foodDetails = foodDetailsRepository.findById(foodDetailsId).orElseThrow(
                ()-> new FoodDetailsNotFoundException("That food Details not in a database")
        );

        return modelMapper.map(foodDetails,FoodDetailsResponse.class);
    }

    public FoodDetailsResponse deleteSpecificFoodDetails(Long foodDetailsId)throws FoodDetailsNotFoundException{

        FoodDetails foodDetails = foodDetailsRepository.findById(foodDetailsId).orElseThrow(
                ()-> new FoodDetailsNotFoundException("That food Details not in a database")
        );

         foodDetailsRepository.deleteById(foodDetailsId);

        return modelMapper.map(foodDetails,FoodDetailsResponse.class);
    }

    @Override
    public ExerciseDetailsResponse createExerciseDetails(ExerciseDetailsDto exerciseDetailsDto) {

        ExerciseDetails exerciseDetails = modelMapper.map(exerciseDetailsDto,ExerciseDetails.class);

        exerciseDetailsRepository.save(exerciseDetails);

        return modelMapper.map(exerciseDetails,ExerciseDetailsResponse.class);
    }


}
