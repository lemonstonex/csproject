package cs.project.evolt.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChargingInfoDTO {
    @JsonProperty("routeId")
    private Long routeId;
    private String stationName;
    private Long stationId;
    private Double chargingTime;
    private Double lat;
    private Double lng;
    private Long plugId;
    private Integer batteryAtStation;
    private Integer chargeTo;
    private Integer energyUsedWhenCharge;
    private Double remainingBatteryAtDestination;

}
