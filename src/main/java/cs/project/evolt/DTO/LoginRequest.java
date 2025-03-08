package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String email;

}
