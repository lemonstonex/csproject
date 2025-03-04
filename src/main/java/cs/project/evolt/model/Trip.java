package cs.project.evolt.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Entity
@Data
@Table(name="Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trip_id", unique=true)
    private long trip_id;

    @Column(name="user_id")
    private long user_id;

    @Column(name="start_location")
    private float start_location;

    @Column(name="dest_location")
    private float dest_location;

    @Column(name="distance")
    private float distance;

    @Column(name="trip_type")
    private String trip_type;

    @Column(name="current_battery")
    private Integer current_battery;

    @Column(name="charge_count")
    private Integer charge_count;
}
