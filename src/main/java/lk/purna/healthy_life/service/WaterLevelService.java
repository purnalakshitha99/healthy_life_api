package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.WaterLevelDto;
import lk.purna.healthy_life.controller.response.WaterLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WaterLevelNotFoundException;

import java.util.List;

public interface WaterLevelService {
    WaterLevelResponse createWaterLevelForUser(Long userId, WaterLevelDto waterLevelDto)throws UserNotFoundException;

    List<WaterLevelResponse> getSpecificUserWaterLevels(Long userId)throws UserNotFoundException, WaterLevelNotFoundException;
}
