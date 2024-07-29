package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.WeightLevelDto;
import lk.purna.healthy_life.controller.response.WeightLevelResponse;
import lk.purna.healthy_life.exception.DateException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.time.LocalDate;

public interface WeightLevelService {
    WeightLevelResponse createWeightForUser(Long userId, WeightLevelDto weightLevelDto)throws UserNotFoundException,DateException;

    WeightLevelResponse getSpecificDateWeightLevel(Long userId, LocalDate date)throws UserNotFoundException, DateNotFoundException;
}
