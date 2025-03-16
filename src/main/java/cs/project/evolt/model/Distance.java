package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="Distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="distance_id", unique=true)
    private long distance_id;

    private float distance;

    @OneToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable=false)
    @JsonBackReference("trip-reference")
    private Trip trip;

    @JsonProperty("station_id")
    public Long getStationId() {
        return (station != null) ? station.getStationId() : null;
    }
}
