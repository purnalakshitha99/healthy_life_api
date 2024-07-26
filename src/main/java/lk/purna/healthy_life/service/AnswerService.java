package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.controller.response.AnswerResponse;
import lk.purna.healthy_life.exception.AnswerNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;

import java.util.List;

public interface AnswerService {
    AnswerDto createAnswerForUser(Long userId, AnswerDto answerDto)throws AnswerNotFoundException;

    AnswerResponse getSpecificUserAnswer(Long userId)throws UserNotFoundException,AnswerNotFoundException;

    List<AnswerResponse> getAllAnswers()throws AnswerNotFoundException;

    AnswerResponse updateSpecificUserAnswer(Long userId,AnswerDto answerDto)throws UserNotFoundException,AnswerNotFoundException;

    AnswerResponse deleteSpecificUserAnswer(Long userId)throws UserNotFoundException,AnswerNotFoundException;
}
