package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.WaterLevelDto;
import lk.purna.healthy_life.controller.response.DailyWaterIntakeResponse;
import lk.purna.healthy_life.controller.response.WaterLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WaterLevelNotFoundException;
import lk.purna.healthy_life.exception.WeightLevelNotFoundException;
import lk.purna.healthy_life.model.Answer;
import lk.purna.healthy_life.model.Gender;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.model.WaterLevel;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.repository.WaterLevelRepository;
import lk.purna.healthy_life.service.WaterLevelService;
import lk.purna.healthy_life.service.WeightLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@AllArgsConstructor
public class WaterLevelServiceImpl implements WaterLevelService {
    private final UserRepository userRepository;
    private final WaterLevelRepository waterLevelRepository;
    private ModelMapper modelMapper;
    private WeightLevelService weightLevelService;

    @Override
    public WaterLevelResponse createWaterLevelForUser(Long userId, WaterLevelDto waterLevelDto) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Not in a db ")
        );

        WaterLevel waterLevel = modelMapper.map(waterLevelDto,WaterLevel.class);
        waterLevel.setDate(LocalDate.now());
        waterLevel.setTime(LocalTime.now().withNano(0));
        waterLevel.setUser(user);

        waterLevelRepository.save(waterLevel);

        return modelMapper.map(waterLevel, WaterLevelResponse.class);

    }

    @Override
    public List<WaterLevelResponse> getSpecificUserWaterLevels(Long userId) throws UserNotFoundException, WaterLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Not in a db ")
        );

        List<WaterLevel> waterLevelList = user.getWaterLevelList();

        if (waterLevelList.isEmpty()){
            throw new WaterLevelNotFoundException("Water level is empty");
        }

      return   waterLevelList.stream().map(waterLevel -> modelMapper.map(waterLevel, WaterLevelResponse.class)).toList();


    }

    @Override
    public DailyWaterIntakeResponse getDailyWaterIntakeByUser(Long userId) throws UserNotFoundException, WeightLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Not in a db ")
        );

        Answer answers = user.getAnswer();

        Float weight = weightLevelService.getLatestWeightByUserId(userId);
        Integer age = answers.getAge();
        Gender gender = answers.getGender();
        Float waterIntake = 0.0F;

        if(age < 18 && gender == Gender.MALE){
            waterIntake = (float) ((weight * 0.06) + 0.5);
        } else if (age < 18 && gender == Gender.FEMALE) {
            waterIntake = (float) ((weight * 0.06) + 0.4);
        } else if (age > 18 && age <= 30 && gender == Gender.MALE) {
            waterIntake = (float) ((weight * 0.03) + 0.5);
        } else if (age > 18 && age <= 30 && gender == Gender.FEMALE) {
            waterIntake = (float) ((weight * 0.03) + 0.4);
        } else if (age > 30 && age <= 50 && gender == Gender.MALE) {
            waterIntake = (float) ((weight * 0.025) + 0.5);
        } else if (age > 30 && age <= 50 && gender == Gender.FEMALE) {
            waterIntake = (float) ((weight * 0.025) + 0.4);
        } else if (age > 50 && gender == Gender.MALE) {
            waterIntake = (float) ((weight * 0.02) + 0.5);
        } else if (age > 50 && gender == Gender.FEMALE) {
            waterIntake = (float) ((weight * 0.02) + 0.4);
        }else {
            return null;
        }

        DailyWaterIntakeResponse dailyWaterIntakeResponse = new DailyWaterIntakeResponse();

        dailyWaterIntakeResponse.setDailyWaterIntake(waterIntake);

        return dailyWaterIntakeResponse;

    }
}
