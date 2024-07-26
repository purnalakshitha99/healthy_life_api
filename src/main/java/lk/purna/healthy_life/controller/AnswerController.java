package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.controller.request.AnswerRq;
import lk.purna.healthy_life.exception.AnswerNotFoundException;
import lk.purna.healthy_life.service.AnswerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
