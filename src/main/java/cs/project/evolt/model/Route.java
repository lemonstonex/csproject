package cs.project.evolt.model;

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

    @Column(name="trip_id")
    private long trip_id;

    @Column(name="station_id")
    private long station_id;

    @Column(name="distance")
    private float distance;

    @Column(name="remaining_battery")
    private Integer remaining_battery;


}
