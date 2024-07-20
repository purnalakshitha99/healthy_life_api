package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.model.FoodDetails;
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
    public List<FoodDetailsResponse> getAllFoods() {

       List<FoodDetails> foodDetailsList = foodDetailsRepository.findAll();

       if (foodDetailsList.isEmpty()){
           System.out.println("food details not found");
       }

    return foodDetailsList.stream().map(foodDetails -> modelMapper.map(foodDetails,FoodDetailsResponse.class)).collect(Collectors.toList());


    }
}
