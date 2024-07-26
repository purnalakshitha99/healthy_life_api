package lk.purna.healthy_life.controller.response;

import lombok.Data;

@Data
public class ActivityLevelResponse {

    private Long id;
    private String activityLevelType;
    private Float level;
}
