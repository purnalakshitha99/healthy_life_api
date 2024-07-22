package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserDto userDto);
}
