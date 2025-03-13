package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="Station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="station_id", unique=true)
    private long station_id;

    @Column(name="station_name")
    private String station_name;

    @Column(name="description")
    private String description;

    @Column(name="opentime")
    private String opentime;

    @Column(name="closetime")
    private String closetime;

    @Column(name="lat")
    private float lat;

    @Column(name="longitude")
    private float longitude;

    @Column(name="address")
    private String address;

    @Column(name = "average_rating")
    private Double average_rating = 0.0; // Default value

    @Column(name="port_available")
    private Integer port_available;

    @OneToMany(mappedBy = "station")
    @JsonManagedReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private List<Reviews> reviewsList;

    @OneToMany(mappedBy = "station")
    @JsonManagedReference("station-reference")
    private List<Plug> plugList;

}
