package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.ExerciseDetails;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getSpecificUser(Long userId) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Details Not in a Database")
        );

        return modelMapper.map(user,UserResponse.class);
    }
}
