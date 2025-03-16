package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class ChargingInfoDTO {
    private Long routeId;
    private String stationName;
    private Long stationId;
    private Double batteryAtStation;
    private Double chargeTo;
    private String energyUsedWhenCharge;
    private Double remainingBatteryAtDestination;
}
