package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="route_id", unique=true)
    private long route_id;

    private Double beforeCharging;
    private Double afterCharging;

    @Column(name="remaining_battery")
    private Integer remaining_battery;

    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    @JsonBackReference("trip_id") // มีหลาย reference ต้องตั้งชื่อ
    private Trip trip;

}
