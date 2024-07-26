package lk.purna.healthy_life.controller;

import lk.purna.healthy_life.controller.dto.ActivityLevelDto;
import lk.purna.healthy_life.controller.request.ActivityLevelRq;
import lk.purna.healthy_life.controller.response.ActivityLevelResponse;
import lk.purna.healthy_life.service.ActivityLevelService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ActivityLevelController {

    private final ActivityLevelService activityLevelService;
    private final ModelMapper modelMapper;

    @PostMapping("/activity_levels")
    public ActivityLevelResponse createActivityLevel(@RequestBody ActivityLevelRq activityLevelRq) {

        ActivityLevelDto activityLevelDto = modelMapper.map(activityLevelRq, ActivityLevelDto.class);

        return activityLevelService.createActivityLevel(activityLevelDto);
    }
}
