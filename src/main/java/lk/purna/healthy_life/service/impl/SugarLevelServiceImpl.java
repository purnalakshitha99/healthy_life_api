package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.SugarLevelDto;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
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
import java.util.List;

@Service
@AllArgsConstructor
public class SugarLevelServiceImpl implements SugarLevelService {

    private final UserRepository userRepository ;
    private final SugarLevelRepository sugarLevelRepository;
    private final ModelMapper modelMapper;
    @Override
    public SugarLevelResponse addSugarLevelByUser(Long userId, SugarLevelDto sugarLevelDto) throws UserNotFoundException,DateNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        LocalDate currentDate = LocalDate.now();

        SugarLevel sugarLevelResults = sugarLevelRepository.findSugarLevelByUserIdAndDate(userId,currentDate);

        if (!(sugarLevelResults == null)){
            throw new DateNotFoundException("That date All Ready Have Sugar Level");
        }

        SugarLevel sugarLevel  = modelMapper.map(sugarLevelDto, SugarLevel.class);
        sugarLevel.setDate(LocalDate.now());
        sugarLevel.setTime(LocalTime.now().withNano(0));
        sugarLevel.setUser(user);


        sugarLevelRepository.save(sugarLevel);

        return modelMapper.map(sugarLevel, SugarLevelResponse.class);
    }

    @Override
    public List<SugarLevelResponse> getSpecificUserSugarLevels(Long userId) throws UserNotFoundException, SugarLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        List<SugarLevel> sugarLevelList = user.getSugarLevelList();

        if (sugarLevelList.isEmpty()){
            throw new SugarLevelNotFoundException("Sugar level is empty");
        }

        return sugarLevelList.stream().map(sugarLevel -> modelMapper.map(sugarLevel, SugarLevelResponse.class)).toList();
    }

    @Override
    public SugarLevelResponse getUserSugarLevelBySpecificDate(Long userId,LocalDate date) throws UserNotFoundException, SugarLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        SugarLevel sugarLevel = sugarLevelRepository.findSugarLevelByUserIdAndDate(userId,date);

        if (sugarLevel == null){
            throw new SugarLevelNotFoundException("That SugarLevels is Empty");
        }

        return modelMapper.map(sugarLevel, SugarLevelResponse.class);

    }

    @Override
    public SugarLevelResponse DeleteUserSugarLevelBySpecificDate(Long userId, LocalDate date) throws UserNotFoundException, SugarLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        SugarLevel sugarLevel = sugarLevelRepository.findSugarLevelByUserIdAndDate(userId,date);

        if (sugarLevel == null){
            throw new SugarLevelNotFoundException("That SugarLevels is Empty");
        }

        sugarLevelRepository.delete(sugarLevel);

        return modelMapper.map(sugarLevel, SugarLevelResponse.class);

    }

    @Override
    public SugarLevelResponse UpdateUserSugarLevelBySpecificDate(Long userId, LocalDate date, SugarLevelDto sugarLevelDto) throws UserNotFoundException, SugarLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        SugarLevel sugarLevel = sugarLevelRepository.findSugarLevelByUserIdAndDate(userId,date);

        if (sugarLevel == null){
            throw new SugarLevelNotFoundException("That SugarLevels is Empty");
        }

        modelMapper.map(sugarLevelDto,sugarLevel);
        sugarLevelRepository.save(sugarLevel);

        return modelMapper.map(sugarLevel, SugarLevelResponse.class);


    }

    @Override
    public void deleteAll() {

        sugarLevelRepository.deleteAll();
    }


}
