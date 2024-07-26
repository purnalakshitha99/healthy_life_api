package lk.purna.healthy_life.service;


import lk.purna.healthy_life.controller.dto.ActivityLevelDto;
import lk.purna.healthy_life.controller.response.ActivityLevelResponse;
import lk.purna.healthy_life.exception.ActivityLevelNotFoundException;

import java.util.List;

public interface ActivityLevelService {
    ActivityLevelResponse createActivityLevel(ActivityLevelDto activityLevelDto);

   List<ActivityLevelResponse> getAllActivityLevel()throws ActivityLevelNotFoundException;
}
