package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Trip")
public class Trip {
    @Id
    @Column(name="trip_id", unique=true)
    private long trip_id;
}
