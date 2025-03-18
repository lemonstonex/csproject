package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name="Result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id", unique = true)
    private Long resultId;

    @Column(name = "remaining_battery")
    private Integer remainingBattery;

    @Column(name = "total_charging_time")
    private Double totalChargingTime; // in hours

    @OneToOne
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", nullable = false)
    @JsonBackReference("route-reference")
    private Route route;

    @OneToMany(mappedBy = "routeResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargingInfo> chargingInfoList;
}
