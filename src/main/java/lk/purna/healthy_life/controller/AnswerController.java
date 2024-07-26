package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.controller.request.AnswerRq;
import lk.purna.healthy_life.controller.response.AnswerResponse;
import lk.purna.healthy_life.controller.response.FoodDetailsResponse;
import lk.purna.healthy_life.exception.AnswerNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.service.AnswerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class AnswerController {

    private ModelMapper modelMapper;
    private AnswerService answerService;

    @PostMapping("/users/{user_id}/answers")
    public ResponseEntity<AnswerDto> createAnswerForUser(@PathVariable("user_id")Long userId, @RequestBody AnswerRq answerRq)throws AnswerNotFoundException {

        AnswerDto answerDto = modelMapper.map(answerRq,AnswerDto.class);

        answerService.createAnswerForUser(userId,answerDto);

        return new ResponseEntity<>(answerDto, HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}/answers")
    public ResponseEntity<AnswerResponse> getSpecificUserAnswer(@PathVariable("user_id")Long userId)throws UserNotFoundException,AnswerNotFoundException {

       AnswerResponse answerResponse =  answerService.getSpecificUserAnswer(userId);

        return new ResponseEntity<>(answerResponse,HttpStatus.FOUND);
    }

    @GetMapping("/users/answers")
    public List<ResponseEntity<List<AnswerResponse>>> getAllAnswers()throws AnswerNotFoundException{

          List <AnswerResponse> answerResponseList = answerService.getAllAnswers();

        return Collections.singletonList(new ResponseEntity<>(answerResponseList, HttpStatus.FOUND));
    }

    @PutMapping("/users/{user_id}/answers")
    public ResponseEntity<AnswerResponse> updateSpecificUserAnswer(@PathVariable("user_id")Long userId,@RequestBody AnswerRq answerRq)throws UserNotFoundException,AnswerNotFoundException{

        AnswerDto answerDto = modelMapper.map(answerRq,AnswerDto.class);
        AnswerResponse answerResponse = answerService.updateSpecificUserAnswer(userId,answerDto);


        return new ResponseEntity<>(answerResponse,HttpStatus.CREATED);
    }



}
