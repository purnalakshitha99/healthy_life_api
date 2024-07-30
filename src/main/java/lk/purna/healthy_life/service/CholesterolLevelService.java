package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

public interface CholesterolLevelService {
    CholesterolLevelResponse addCholesterolLevelsByUser(Long userId, CholesterolLevelDto cholesterolLevelDto)throws UserNotFoundException, DateNotFoundException;
}
