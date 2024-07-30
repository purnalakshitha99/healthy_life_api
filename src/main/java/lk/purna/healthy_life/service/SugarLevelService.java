package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SugarLevelService {
    SugarLevelResponse addSugarLevelByUser(Long userId, SugarLevelDto sugarLevelDto)throws UserNotFoundException,DateNotFoundException;

    List<SugarLevelResponse> getSpecificUserSugarLevels(Long userId)throws UserNotFoundException, SugarLevelNotFoundException;

    SugarLevelResponse getUserSugarLevelBySpecificDate(Long userId, LocalDate date)throws UserNotFoundException, SugarLevelNotFoundException;
}
