package lk.purna.healthy_life.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {

    private String token;
}
