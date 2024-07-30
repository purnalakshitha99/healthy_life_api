package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.WeightLevelDto;
import lk.purna.healthy_life.controller.response.WeightLevelResponse;
import lk.purna.healthy_life.exception.DateException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.exception.WeightLevelNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface WeightLevelService {
    WeightLevelResponse createWeightForUser(Long userId, WeightLevelDto weightLevelDto)throws UserNotFoundException,DateException;

    WeightLevelResponse getSpecificDateWeightLevel(Long userId, LocalDate date)throws UserNotFoundException, DateNotFoundException;

    List<WeightLevelResponse> getSpecificUserWeightLevels(Long userId)throws UserNotFoundException, WeightLevelNotFoundException;

    Float getLatestWeightByUserId(Long userId)throws UserNotFoundException,WeightLevelNotFoundException;
}
