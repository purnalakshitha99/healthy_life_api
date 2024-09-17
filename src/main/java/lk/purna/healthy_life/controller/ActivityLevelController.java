package lk.purna.healthy_life.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.purna.healthy_life.controller.dto.ActivityLevelDto;
import lk.purna.healthy_life.controller.request.ActivityLevelRq;
import lk.purna.healthy_life.controller.response.ActivityLevelResponse;
import lk.purna.healthy_life.exception.ActivityLevelNotFoundException;
import lk.purna.healthy_life.service.ActivityLevelService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class ActivityLevelController {

    private final ActivityLevelService activityLevelService;
    private final ModelMapper modelMapper;

    @RolesAllowed("ADMIN")
    @PostMapping("/activity_levels")
    public ActivityLevelResponse createActivityLevel(@RequestBody ActivityLevelRq activityLevelRq) {

        ActivityLevelDto activityLevelDto = modelMapper.map(activityLevelRq, ActivityLevelDto.class);

        return activityLevelService.createActivityLevel(activityLevelDto);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/activity_levels")
    public List<ResponseEntity<List<ActivityLevelResponse>>> getAllActivityLevel()throws ActivityLevelNotFoundException {

        List<ActivityLevelResponse> activityLevelResponseList = activityLevelService.getAllActivityLevel();

        return Collections.singletonList(new ResponseEntity<>(activityLevelResponseList,HttpStatus.FOUND));
    }
}
