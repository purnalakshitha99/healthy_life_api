package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CholesterolLevelService {
    CholesterolLevelResponse addCholesterolLevelsByUser(Long userId, CholesterolLevelDto cholesterolLevelDto)throws UserNotFoundException, DateNotFoundException;

    List<CholesterolLevelResponse> getSpecificUserCholesterolLevels(Long userId)throws UserNotFoundException, CholesterolLevelNotFoundException;

    CholesterolLevelResponse getUserCholesterolLevelBySpecificDate(Long userId, LocalDate date)throws UserNotFoundException,CholesterolLevelNotFoundException;
}
