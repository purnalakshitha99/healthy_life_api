package lk.purna.healthy_life.service;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.exception.AnswerNotFoundException;

public interface AnswerService {
    AnswerDto createAnswerForUser(Long userId, AnswerDto answerDto)throws AnswerNotFoundException;
}
