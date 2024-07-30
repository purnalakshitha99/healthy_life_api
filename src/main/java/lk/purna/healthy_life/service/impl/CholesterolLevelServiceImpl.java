package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.CholesterolLevelDto;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.controller.response.SugarLevelResponse;
import lk.purna.healthy_life.controller.response.UserResponse;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.SugarLevelNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.CholesterolLevel;
import lk.purna.healthy_life.model.SugarLevel;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.CholesterolLevelRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.CholesterolLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CholesterolLevelServiceImpl implements CholesterolLevelService {

    private final UserRepository userRepository;
    private final CholesterolLevelRepository cholesterolLevelRepository;
    private final ModelMapper modelMapper;

    @Override
    public CholesterolLevelResponse addCholesterolLevelsByUser(Long userId, CholesterolLevelDto cholesterolLevelDto) throws UserNotFoundException, DateNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        LocalDate currentDate = LocalDate.now();

        CholesterolLevel cholesterolLevelResults = cholesterolLevelRepository.findCholesterolLevelByUserIdAndDate(userId,currentDate);

        if (!(cholesterolLevelResults == null)){
            throw new DateNotFoundException("That date All Ready Have Cholesterol Level");
        }

        CholesterolLevel cholesterolLevel  = modelMapper.map(cholesterolLevelDto, CholesterolLevel.class);
        cholesterolLevel.setDate(LocalDate.now());
        cholesterolLevel.setTime(LocalTime.now().withNano(0));
        cholesterolLevel.setUser(user);


        cholesterolLevelRepository.save(cholesterolLevel);

        CholesterolLevelResponse cholesterolLevelResponse =  modelMapper.map(cholesterolLevel,CholesterolLevelResponse.class);
        cholesterolLevelResponse.setUserId(cholesterolLevel.getUser().getId());

        return cholesterolLevelResponse;
    }

    @Override
    public List<CholesterolLevelResponse> getSpecificUserCholesterolLevels(Long userId) throws UserNotFoundException, CholesterolLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

       List <CholesterolLevel> cholesterolLevelList = user.getCholesterolLevelList();
        if (cholesterolLevelList == null){
            throw new CholesterolLevelNotFoundException("That Cholesterol level is Empty");
        }

        return cholesterolLevelList.stream().map(cholesterolLevel -> modelMapper.map(cholesterolLevel,CholesterolLevelResponse.class)).toList();

    }
}
