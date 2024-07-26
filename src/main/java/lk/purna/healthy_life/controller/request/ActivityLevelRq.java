package lk.purna.healthy_life.controller.request;

import lombok.Data;

@Data
public class ActivityLevelRq {

    private String activityLevelType;
    private Float level;
}
