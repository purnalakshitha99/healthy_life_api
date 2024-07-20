package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.FoodDetailsDto;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.model.FoodDetails;
import lk.purna.healthy_life.repository.FoodDetailsRepository;
import lk.purna.healthy_life.service.FoodDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
