package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private long stationId;

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

    @Column(name = "average_rating",nullable = false)
    private Double average_rating = 0.0; // Default value

    @Column(name="port_available")
    private Integer port_available;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    @JsonManagedReference("station-reference") // Matching reference name
    private List<StationRating> stationRatings;

    @OneToMany(mappedBy = "station")
    @JsonManagedReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private List<Reviews> reviewsList;

    @OneToMany(mappedBy = "station")
    @JsonManagedReference("station-reference")
    private List<Plug> plugList;

    @Transient
    @JsonProperty("rating_count")
    public int getRatingCount() {
        return stationRatings != null ? stationRatings.size() : 0;
    }

    @Transient
    @JsonProperty("average_rating")
    public double getAverageRating() {
        if (stationRatings == null || stationRatings.isEmpty()) {
            return 0.0;
        }
        double sum = stationRatings.stream().mapToDouble(StationRating::getRating).sum();
        return Math.round((sum / stationRatings.size()) * 10.0) / 10.0;
    }

}
