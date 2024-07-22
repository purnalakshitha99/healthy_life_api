package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;

public interface UserService {
    UserResponse createUser(UserDto userDto);

    UserResponse getSpecificUser(Long userId)throws UserNotFoundException;
}
