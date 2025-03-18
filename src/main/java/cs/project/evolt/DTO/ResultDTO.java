package cs.project.evolt.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResultDTO {

    private Long tripId;
    private Long routeId;
    private Integer remainingBattery;
    private List<ChargingInfoDTO> chargingInfoList;

}
