package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Route")
public class Route {
    @Id
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
