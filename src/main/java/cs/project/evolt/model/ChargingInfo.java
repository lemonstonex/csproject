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
    @JoinColumn(name="route_id", nullable=true)
    @JsonBackReference("route-reference")
    private Route route;

    @Column(name="station_name")
    private String stationName;

    @Column(name="station_id")
    private long stationId;

    @Column(name="plug_id")
    private long plug_id;

    @Column(name="battery_at_station")
    private Integer batteryAtStation;

    @Column(name="charge_to")
    private Integer chargeTo;

    @Column(name="energy_used_when_charge")
    private Integer energyUsedWhenCharge;

    @Column(name = "charging_time")
    private Double chargingTime;

    private Float lat;
    private Float lng;

    @ManyToOne
    @JoinColumn(name = "result_id")
    @JsonBackReference("result-reference")
    private Result result;

}