package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.WaterLevelDto;
import lk.purna.healthy_life.controller.response.WaterLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WaterLevelNotFoundException;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.model.WaterLevel;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.repository.WaterLevelRepository;
import lk.purna.healthy_life.service.WaterLevelService;
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
}
