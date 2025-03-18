package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class ChargingInfoDTO {
    private Long routeId;
    private String stationName;
    private Long stationId;
    private Double batteryAtStation;
    private Double chargeTo;
    private Double energyUsedWhenCharge;
    private Double remainingBatteryAtDestination;
//    private Integer plugId;
}
