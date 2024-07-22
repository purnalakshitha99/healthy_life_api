package lk.purna.healthy_life.controller.request;

import lombok.Data;

@Data
public class UserRq {

    private String name;
    private String email;
    private String password;
}
