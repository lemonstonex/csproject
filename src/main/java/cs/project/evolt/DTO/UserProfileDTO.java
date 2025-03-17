package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String password;
    private String email;
    private long modelId;
}
