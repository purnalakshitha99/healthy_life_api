package lk.purna.healthy_life.controller;



import jakarta.annotation.security.RolesAllowed;
import lk.purna.healthy_life.controller.request.UserAuthRequestDTO;
import lk.purna.healthy_life.controller.response.UserLoginResponseDTO;
import lk.purna.healthy_life.exception.InvalidCredentialsException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) throws UserNotFoundException, InvalidCredentialsException {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        String email = requestDTO.getUsername();

       Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));

        List<String> roles = new ArrayList<>();
       if (userOptional.isEmpty()){
           throw new UserNotFoundException("that user not in a db");
       }else{
           User user = userOptional.get();

           roles.add("ROLE_"+user.getRoles());





           //authenticated user

           user.setUsername(requestDTO.getUsername());
           System.out.println("password : "+user.getPassword());
           System.out.println("password req : "+requestDTO.getPassword());
           System.out.println("roleeeeeeeeeeeeeeee"+user.getRoles());
           System.out.println("usernameeee"+user.getUsername());

           // Check if the provided password matches the stored hashed password
           if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
               System.out.println("waradiiiiiiiiiiiiiiiiiiiiiiiiiiii");
               throw new InvalidCredentialsException("Invalid credentials");
           }

           Map<String, Object> extraClaims = new HashMap<>();
           extraClaims.put("username", user.getEmail());
           extraClaims.put("roles", user.getRoles());
           extraClaims.put("name", user.getName());

           String token = jwtService.generateToken(user, extraClaims);
           return UserLoginResponseDTO.builder()
                   .token(token)
                   .build();
       }




//        List<String> roles = new ArrayList<>();
//        if (requestDTO.getUsername().equals("user1")) {
//            roles.add("ROLE_USER");
//
//            System.out.println("user1 awa");
//        }
//        if (requestDTO.getUsername().equals("user2")) {
////            roles.addAll(List.of("ROLE_USER", "ROLE_ADMIN"));
//            roles.add("ROLE_ADMIN");
//        }


    }

//    @RolesAllowed("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @PostMapping("/admin")
    public String sayHiAdmin(){
        return "Hello Admin";
    }


//    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
//    @RolesAllowed("ROLE_USER")
    @RolesAllowed({"USER","ADMIN"})
    @PostMapping("/user")
    public String sayHiUser(){
        return "Hello User";
    }
}


//package com.example.jwt_spring_ex.controller;
//
//import com.example.jwt_spring_ex.controller.request.UserAuthRequestDTO;
//import com.example.jwt_spring_ex.controller.response.UserLoginResponseDTO;
//import com.example.jwt_spring_ex.exception.InvalidCredentialsException;
//import com.example.jwt_spring_ex.exception.UserNotFoundException;
//import com.example.jwt_spring_ex.model.User;
//import com.example.jwt_spring_ex.repository.UserRepository;
//import com.example.jwt_spring_ex.security.JwtService;
//import jakarta.annotation.security.RolesAllowed;
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.*;
//
//@RestController
//@AllArgsConstructor
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//
//    @PostMapping("/authenticate")
//    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) throws UserNotFoundException, InvalidCredentialsException {
//        System.out.println(" ====authenticate user " + requestDTO.getUsername());
//
//        String email = requestDTO.getUsername();
//
//        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));
//
//        List<String> roles = new ArrayList<>();
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException("User not found in the database");
//        } else {
//            User user = userOptional.get();
//
//            roles.add("ROLE_" + user.getRoles());
//
//            user.setUsername(requestDTO.getUsername());
//
//            // authenticated user
//            System.out.println("password from db: " + user.getPassword());
//            System.out.println("password from request: " + requestDTO.getPassword());
//
//            // Check if the provided password matches the stored hashed password
//            if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
//                System.out.println("Invalid credentials");
//                throw new InvalidCredentialsException("Invalid credentials");
//            }
//
//            // Add extra claims to the JWT token
//            Map<String, Object> extraClaims = new HashMap<>();
//            extraClaims.put("username", user.getUsername());
//            extraClaims.put("roles", user.getRoles());
//            extraClaims.put("name", user.getName());
//
//            // Generate JWT token
//            String token = jwtService.generateToken(user, extraClaims);
//            return UserLoginResponseDTO.builder()
//                    .token(token)
//                    .build();
//        }
//    }
//
//    @RolesAllowed("ROLE_ADMIN")
//    @PostMapping("/admin")
//    public String sayHiAdmin() {
//        return "Hello Admin";
//    }
//
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
//    @PostMapping("/user")
//    public String sayHiUser() {
//        return "Hello User";
//    }
//}
