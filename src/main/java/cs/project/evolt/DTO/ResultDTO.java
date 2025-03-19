package cs.project.evolt.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResultDTO {
    private long modelId;
    private Double totalChargingTime;
    private List<ChargingInfoDTO> chargingInfoList;

}
