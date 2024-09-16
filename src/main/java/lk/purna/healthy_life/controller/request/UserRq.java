package lk.purna.healthy_life.controller.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.purna.healthy_life.model.ROLES;
import lombok.Data;

@Data
public class UserRq {

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;
}
