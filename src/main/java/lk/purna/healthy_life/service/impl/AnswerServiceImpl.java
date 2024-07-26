package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.exception.AnswerNotFoundException;
import lk.purna.healthy_life.model.Answer;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.AnswerRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.AnswerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private ModelMapper modelMapper;

    @Override
    public AnswerDto createAnswerForUser(Long userId, AnswerDto answerDto) throws AnswerNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new AnswerNotFoundException("That User Not in a db")
        );

        Answer answer = modelMapper.map(answerDto, Answer.class);

        answer.setUser(user);
        answer = answerRepository.save(answer);

        return modelMapper.map(answer,AnswerDto.class);
    }
}
