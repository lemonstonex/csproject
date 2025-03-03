package cs.project.evolt.Request;

import lombok.Data;


@Data

public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private long model_id;
}
