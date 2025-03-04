package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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


}
