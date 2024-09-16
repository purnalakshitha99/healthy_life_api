package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.response.ExerciseDetailsResponse;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.exception.FoodDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.ExerciseDetails;
import lk.purna.healthy_life.model.FoodDetails;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

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

    @Override
    public UserResponse updateUser(Long userID, UserDto userDto) throws UserNotFoundException {

        User user = userRepository.findById(userID).orElseThrow(
                ()->  new UserNotFoundException("That UserNot In a db")
        );

        modelMapper.map(userDto,user);

        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);

    }

    public UserResponse deleteSpecificUser(Long userId)throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That food Details not in a database")
        );

        userRepository.deleteById(userId);

        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() throws UserNotFoundException {

        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()){
            throw new UserNotFoundException("user are empty in the db");
        }

        return userList.stream().map(user -> modelMapper.map(user,UserResponse.class)).toList();
    }


}
