package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.FoodSelectionDto;
import lk.purna.healthy_life.controller.request.FoodSelectionRq;
import lk.purna.healthy_life.controller.response.FoodResponse;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.Food;
import lk.purna.healthy_life.model.FoodDetails;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.FoodDetailsRepository;
import lk.purna.healthy_life.repository.FoodRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.FoodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private ModelMapper modelMapper;
    private final FoodDetailsRepository foodDetailsRepository;

    @Override
    public List<FoodResponse> createFoodForUser(Long userId, List<FoodSelectionDto> foodSelectionDtoList) throws UserNotFoundException {


        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        List<FoodResponse> responses = foodSelectionDtoList.stream().map(foodSelectionRq ->{
            FoodDetails foodDetails = foodDetailsRepository.findById(foodSelectionRq.getFoodId()).orElseThrow(
                    ()-> new RuntimeException("That Food Not in a db: "+foodSelectionRq.getFoodId())
            );


            LocalDate currentDate = LocalDate.now();



            Float ratio = foodSelectionRq.getAmountInGram() / foodDetails.getAmount();

            Food food = new Food();

            food.setAmount(foodSelectionRq.getAmountInGram());
            food.setFoodType(foodSelectionRq.getFoodType());
            food.setDate(currentDate);
            food.setCalories(foodDetails.getCalories() * ratio);
            food.setFat(foodDetails.getFat() * ratio);
            food.setCarbs(foodDetails.getCarbs() * ratio);
            food.setProtein(foodDetails.getProtein() * ratio);
            food.setName(foodDetails.getName());
            food.setAmount(foodSelectionRq.getAmountInGram());
            food.setFoodType(foodSelectionRq.getFoodType());

           food =  foodRepository.save(food);

           user.getFoodList().add(food);

           return modelMapper.map(food, FoodResponse.class);
        } ) .collect(Collectors.toList());

        userRepository.save(user);
        return responses;
    }
}

