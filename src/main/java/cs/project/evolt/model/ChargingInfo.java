package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="ChargingInfo")
public class ChargingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="charging_info_id", unique=true)
    private long chargingInfoId;

    @ManyToOne
    @JoinColumn(name="route_id", nullable=false)
    @JsonBackReference("route-reference")
    private Route route;

    @Column(name="station_name")
    private String stationName;

    @Column(name="station_id")
    private long stationId;

//    @Column(name="plug_id")
//    private Integer plugId;

    @Column(name="battery_at_station")
    private Integer batteryAtStation;

    @Column(name="charge_to")
    private Integer chargeTo;

    @Column(name="energy_used_when_charge")
    private Integer energyUsedWhenCharge;

    @Column(name = "charging_time")
    private Double chargingTime;

    @ManyToOne
    @JoinColumn(name = "route_result_id", referencedColumnName = "result_id")
    @JsonBackReference("charging-info-reference")
    private Result routeResult;
}