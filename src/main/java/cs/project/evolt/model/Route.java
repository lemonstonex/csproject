package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Route")
public class Route {
    @Id
    @Column(name="route_id", unique=true)
    private long route_id;
}
