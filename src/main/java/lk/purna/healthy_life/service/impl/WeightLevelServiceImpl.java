package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.WeightLevelDto;
import lk.purna.healthy_life.controller.response.WeightLevelResponse;
import lk.purna.healthy_life.exception.DateException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.model.WeightLevel;
import lk.purna.healthy_life.repository.AnswerRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.repository.WeightLevelRepository;
import lk.purna.healthy_life.service.WeightLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeightLevelServiceImpl implements WeightLevelService {

    private UserRepository userRepository;
    private WeightLevelRepository weightLevelRepository;
    private ModelMapper modelMapper;

    @Override
    public WeightLevelResponse createWeightForUser(Long userId, WeightLevelDto weightLevelDto) throws UserNotFoundException, DateException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        LocalDate currentDate = LocalDate.now();

        Optional<WeightLevel> optionalWeightLevel = Optional.ofNullable(weightLevelRepository.findWeightLevelByDateAndUserId(currentDate, userId));

        if (optionalWeightLevel.isPresent()){
            throw new DateException("That date all ready have weight level ");
        }

        WeightLevel weightLevel = modelMapper.map(weightLevelDto,WeightLevel.class);

        weightLevel.setDate(currentDate);
        weightLevel.setUser(user);

        weightLevelRepository.save(weightLevel);

        return  modelMapper.map(weightLevel, WeightLevelResponse.class);

    }

    @Override
    public WeightLevelResponse getSpecificDateWeightLevel(Long userId, LocalDate date) throws UserNotFoundException, DateNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        WeightLevel weightLevel = weightLevelRepository.findWeightLevelByDateAndUserId(date,userId);

        if (weightLevel == null){
            throw new DateNotFoundException("that date not having any weight level details ");
        }

        return modelMapper.map(weightLevel,WeightLevelResponse.class);
    }
}
