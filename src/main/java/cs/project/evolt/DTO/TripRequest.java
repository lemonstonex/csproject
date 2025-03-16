package cs.project.evolt.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TripRequest {
    private float total_distance;
    private Integer current_battery;
    private Long model_id;
    private List<DistanceRequest> distancesList;
}
