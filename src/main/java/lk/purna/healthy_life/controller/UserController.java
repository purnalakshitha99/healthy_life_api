package lk.purna.healthy_life.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.request.UserRq;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRq userRq){


        System.out.println("role :"+userRq.getRoles());
        UserDto userDto = modelMapper.map(userRq,UserDto.class);
        UserResponse userResponse = userService.createUser(userDto);


        return ResponseEntity.created(URI.create("users")).body(userResponse);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> getSpecificUser(@PathVariable("user_id")Long userId)throws UserNotFoundException {

        UserResponse userResponse  = userService.getSpecificUser(userId);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }

    @RolesAllowed({"USER","ADMIN"})
    @PutMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("user_id")Long userID, @RequestBody UserRq userRq)throws UserNotFoundException {

        UserDto userDto = modelMapper.map(userRq,UserDto.class);
        UserResponse userResponse = userService.updateUser(userID,userDto);

        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> deleteSpecificUser(@PathVariable("user_id")Long userId)throws UserNotFoundException{

        UserResponse userResponse = userService.deleteSpecificUser(userId);

        return new ResponseEntity<>(userResponse,HttpStatus.FOUND);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/users")
    public List<ResponseEntity<List<UserResponse>>> getAllUsers()throws UserNotFoundException {

        List<UserResponse> userResponseList = userService.getAllUsers();

        return Collections.singletonList(new ResponseEntity<>(userResponseList, HttpStatus.FOUND));
    }

}
