package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Bookmark")
public class Bookmark {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookmark_id", unique=true)
    private long bookmarkId;


    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-reference") // มีหลาย reference ต้องตั้งชื่อ
    private User user;

    @JsonProperty("station_id")
    public long getStationId() {
        return station != null ? station.getStationId() : 0;
    }

    @JsonProperty("user_id")
    public long getUserId() {
        return user != null ? user.getUserId() : 0;
    }

    @JsonProperty("station_name")
    public String getStationName() {
        return station != null ? station.getStation_name() : null;
    }

    @JsonProperty("address")
    public String getAddress() {
        return station != null ? station.getAddress() : null;
    }


    @JsonProperty("port_available")
    public Integer getPortAvailable() {
        return station != null && station.getPort_available() != null ? station.getPort_available() : 0;
    }

    @JsonProperty("rating_count")
    public int getRatingCount() {
        return station != null ? station.getRatingCount() : 0;
    }

    @JsonProperty("average_rating")
    public Double getAverageRating() {
        return station != null ? station.getAverageRating() : 0.0;
    }


}
