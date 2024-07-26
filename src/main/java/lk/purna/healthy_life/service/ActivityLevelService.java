package lk.purna.healthy_life.service;


import lk.purna.healthy_life.controller.dto.ActivityLevelDto;
import lk.purna.healthy_life.controller.response.ActivityLevelResponse;

public interface ActivityLevelService {
    ActivityLevelResponse createActivityLevel(ActivityLevelDto activityLevelDto);
}
