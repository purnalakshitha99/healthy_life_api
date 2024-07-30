package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.BloodPressureLevelDto;
import lk.purna.healthy_life.controller.response.BloodPressureLevelResponse;
import lk.purna.healthy_life.controller.response.CholesterolLevelResponse;
import lk.purna.healthy_life.exception.BloodPressureLevelNotFoundException;
import lk.purna.healthy_life.exception.CholesterolLevelNotFoundException;
import lk.purna.healthy_life.exception.DateNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.BloodPressureLevel;
import lk.purna.healthy_life.model.CholesterolLevel;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.BloodPressureLevelRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.BloodPressureLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BloodPressureLevelServiceImpl implements BloodPressureLevelService {

    private ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BloodPressureLevelRepository bloodPressureLevelRepository;

    public BloodPressureLevelResponse addBloodPressureForUser(Long userId, BloodPressureLevelDto bloodPressureLevelDto)throws UserNotFoundException, DateNotFoundException{

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That User Not in a db")
        );


        BloodPressureLevel bloodPressureLevelResults = bloodPressureLevelRepository.findBloodPressureLevelByUserIdAndDate(userId, LocalDate.now());

        if (!(bloodPressureLevelResults == null)){
            throw new DateNotFoundException("That Date All Ready have blood pressure level");
        }

        BloodPressureLevel bloodPressureLevel = modelMapper.map(bloodPressureLevelDto,BloodPressureLevel.class);
        bloodPressureLevel.setUser(user);
        bloodPressureLevel.setTime(LocalTime.now().withNano(0));
        bloodPressureLevel.setDate(LocalDate.now());

        bloodPressureLevelRepository.save(bloodPressureLevel);

        return modelMapper.map(bloodPressureLevel, BloodPressureLevelResponse.class);


    }

    @Override
    public List<BloodPressureLevelResponse> getSpecificUserBloodPressureLevels(Long userId) throws UserNotFoundException, BloodPressureLevelNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        List <BloodPressureLevel> bloodPressureLevelList = user.getBloodPressureLevelList();

        if (bloodPressureLevelList.isEmpty()){
            throw new BloodPressureLevelNotFoundException("That BloodPressure level is Empty");
        }

        return bloodPressureLevelList.stream().map(bloodPressureLevel -> modelMapper.map(bloodPressureLevel, BloodPressureLevelResponse.class)).toList();

    }

    @Override
    public BloodPressureLevelResponse getUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date) throws UserNotFoundException, BloodPressureLevelNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        BloodPressureLevel bloodPressureLevel = bloodPressureLevelRepository.findBloodPressureLevelByUserIdAndDate(userId,date);

        if (bloodPressureLevel == null){
            throw new BloodPressureLevelNotFoundException("That Blood Pressure level is Empty");
        }

        return modelMapper.map(bloodPressureLevel,BloodPressureLevelResponse.class);

    }

    @Override
    public BloodPressureLevelResponse DeleteUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date) throws UserNotFoundException, BloodPressureLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user not in a db")
        );

        BloodPressureLevel bloodPressureLevel = bloodPressureLevelRepository.findBloodPressureLevelByUserIdAndDate(userId,date);

        if (bloodPressureLevel == null){
            throw new BloodPressureLevelNotFoundException("That BloodPressure levels is Empty");
        }

        bloodPressureLevelRepository.delete(bloodPressureLevel);

        return modelMapper.map(bloodPressureLevel,BloodPressureLevelResponse.class);
    }

    @Override
    public BloodPressureLevelResponse UpdateUserBloodPressureLevelBySpecificDate(Long userId, LocalDate date, BloodPressureLevelDto bloodPressureLevelDto) throws UserNotFoundException, BloodPressureLevelNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That user Not in a db")
        );

        BloodPressureLevel bloodPressureLevelResults = bloodPressureLevelRepository.findBloodPressureLevelByUserIdAndDate(userId,date);

        if (bloodPressureLevelResults == null){
            throw new BloodPressureLevelNotFoundException("BloodPressure levels are empty");
        }

        modelMapper.map(bloodPressureLevelDto,bloodPressureLevelResults);
        bloodPressureLevelRepository.save(bloodPressureLevelResults);

        return modelMapper.map(bloodPressureLevelResults,BloodPressureLevelResponse.class);
    }

    @Override
    public void deleteAll() {

        bloodPressureLevelRepository.deleteAll();
    }
}
