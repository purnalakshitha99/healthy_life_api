package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.BloodPressureLevelDto;
import lk.purna.healthy_life.controller.response.BloodPressureLevelResponse;
import lk.purna.healthy_life.exception.BloodPressureLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;


public interface BloodPressureLevelService {

    BloodPressureLevelResponse addBloodPressureForUser(Long userId, BloodPressureLevelDto bloodPressureLevelDto)throws UserNotFoundException, DateNotFoundException;

    List<BloodPressureLevelResponse> getSpecificUserBloodPressureLevels(Long userId)throws UserNotFoundException, BloodPressureLevelNotFoundException;

    BloodPressureLevelResponse getUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date)throws UserNotFoundException,BloodPressureLevelNotFoundException;

    BloodPressureLevelResponse DeleteUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date)throws UserNotFoundException,BloodPressureLevelNotFoundException;

    BloodPressureLevelResponse UpdateUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date, BloodPressureLevelDto bloodPressureLevelDto)throws UserNotFoundException,BloodPressureLevelNotFoundException;

    void deleteAll();
}
