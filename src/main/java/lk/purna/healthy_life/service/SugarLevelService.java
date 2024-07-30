package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.util.List;

public interface SugarLevelService {
    SugarLevelResponse addSugarLevelByUser(Long userId, SugarLevelDto sugarLevelDto)throws UserNotFoundException;

    List<SugarLevelResponse> getSpecificUserSugarLevels(Long userId)throws UserNotFoundException, SugarLevelNotFoundException;
}
