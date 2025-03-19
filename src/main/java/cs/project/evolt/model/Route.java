package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="route_id", unique=true)
    private long route_id;

    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    @JsonBackReference("trip-reference")
    private Trip trip;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("route-reference")
    private List<ChargingInfo> chargingList;

    @Column(name="remaining_battery")
    private Integer remaining_battery;

}
