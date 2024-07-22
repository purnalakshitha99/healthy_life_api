package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.UserDto;
import lk.purna.healthy_life.controller.request.UserRq;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRq userRq){

        UserDto userDto = modelMapper.map(userRq,UserDto.class);
        UserResponse userResponse = userService.createUser(userDto);

        return ResponseEntity.created(URI.create("users")).body(userResponse);
    }
}
