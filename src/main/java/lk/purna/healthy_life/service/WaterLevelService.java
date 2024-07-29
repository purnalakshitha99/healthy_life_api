package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.WaterLevelDto;
import lk.purna.healthy_life.controller.response.WaterLevelResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;

public interface WaterLevelService {
    WaterLevelResponse createWaterLevelForUser(Long userId, WaterLevelDto waterLevelDto)throws UserNotFoundException;
}
