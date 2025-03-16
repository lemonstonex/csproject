package cs.project.evolt.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CandidateRouteDTO {
    private List<Map<String, Object>> routeList;
}
