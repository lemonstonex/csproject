package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Station")
public class Station {
    @Id
    @Column(name="station_id", unique=true)
    private long station_id;

    private String station_name;
}
