package lk.purna.healthy_life.service.impl;


import lk.purna.healthy_life.controller.dto.ActivityLevelDto;
import lk.purna.healthy_life.controller.response.ActivityLevelResponse;
import lk.purna.healthy_life.exception.ActivityLevelNotFoundException;
import lk.purna.healthy_life.model.ActivityLevel;
import lk.purna.healthy_life.repository.ActivityLevelRepository;
import lk.purna.healthy_life.service.ActivityLevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ActivityLevelServiceImpl implements ActivityLevelService {

    private final ActivityLevelRepository activityLevelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ActivityLevelResponse createActivityLevel(ActivityLevelDto activityLevelDto) {

        ActivityLevel activityLevel = modelMapper.map(activityLevelDto,ActivityLevel.class);
        activityLevelRepository.save(activityLevel);

        return modelMapper.map(activityLevel, ActivityLevelResponse.class);
    }

    public List<ActivityLevelResponse> getAllActivityLevel()throws ActivityLevelNotFoundException {

        List<ActivityLevel> activityLevelList  = activityLevelRepository.findAll();

        if (activityLevelList.isEmpty()){
            throw new ActivityLevelNotFoundException("activity levels empty");
        }

        return activityLevelList.stream().map(activityLevel -> modelMapper.map(activityLevel,ActivityLevelResponse.class)).toList();
    }
}