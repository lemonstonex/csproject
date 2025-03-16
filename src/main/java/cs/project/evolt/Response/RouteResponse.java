package cs.project.evolt.Response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RouteResponse {
    private Long tripId;
    private Integer currentBattery;
    private List<Map<String, Object>> routeList;
}
