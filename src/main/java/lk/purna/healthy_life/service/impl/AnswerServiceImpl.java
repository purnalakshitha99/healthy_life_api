package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.AnswerDto;
import lk.purna.healthy_life.controller.response.AnswerResponse;
import lk.purna.healthy_life.exception.AnswerNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.Answer;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.AnswerRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.AnswerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.attribute.UnmodifiableSetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public AnswerResponse getSpecificUserAnswer(Long userId) throws UserNotFoundException,AnswerNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        Answer answer = user.getAnswer();

        if (answer == null){
            throw new AnswerNotFoundException("Answer is empty");
        }

        return modelMapper.map(answer,AnswerResponse.class);
    }

    @Override
    public List<AnswerResponse> getAllAnswers() throws AnswerNotFoundException {

        List<Answer> answerList = answerRepository.findAll();

        if (answerList.isEmpty()){
            throw new AnswerNotFoundException("Answer db is empty");
        }

         List<AnswerResponse> answerResponseList = new ArrayList<>();

        for (Answer answer:answerList){

            AnswerResponse answerResponse = modelMapper.map(answer,AnswerResponse.class);
            answerResponseList.add(answerResponse);
        }

        return answerResponseList;

    }

    public AnswerResponse updateSpecificUserAnswer(Long userId,AnswerDto answerDto)throws UserNotFoundException,AnswerNotFoundException{

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Not in a Db")
        );

        Answer answer = user.getAnswer();

        modelMapper.map(answerDto,answer);

        answer = answerRepository.save(answer);

        System.out.println("level" +answer.getActivityLevelType());
        System.out.println("age"+answer.getAge());
        System.out.println("weight"+answer.getGoalWeight());
        System.out.println("gym"+answer.getGymStatus());

        return modelMapper.map(answer,AnswerResponse.class);

    }

    @Override
    public AnswerResponse deleteSpecificUserAnswer(Long userId) throws UserNotFoundException, AnswerNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("that user not in a db")
        );

        Answer answer = user.getAnswer();

        if (answer == null){
            throw new AnswerNotFoundException("Answer is empty");
        }

        answerRepository.delete(answer);

        return modelMapper.map(answer,AnswerResponse.class);
    }


}
