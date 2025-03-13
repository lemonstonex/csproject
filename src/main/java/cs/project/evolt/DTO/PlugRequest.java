package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class PlugRequest {
    private String plug_type;
    private String plug_id;
    private float maxKwh;
    private String pricing;
}
