package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.SugarLevel;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.SugarLevelRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.SugarLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class SugarLevelServiceImpl implements SugarLevelService {

    private final UserRepository userRepository ;
    private final SugarLevelRepository sugarLevelRepository;
    private final ModelMapper modelMapper;
    @Override
    public SugarLevelResponse addSugarLevelByUser(Long userId, SugarLevelDto sugarLevelDto) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        SugarLevel sugarLevel  = modelMapper.map(sugarLevelDto, SugarLevel.class);
        sugarLevel.setDate(LocalDate.now());
        sugarLevel.setTime(LocalTime.now().withNano(0));
        sugarLevel.setUser(user);

        sugarLevelRepository.save(sugarLevel);

        return modelMapper.map(sugarLevel,SugarLevelResponse.class);
    }
}
