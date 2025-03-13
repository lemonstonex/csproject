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
        return station != null ? station.getStation_id() : 0;
    }

    @JsonProperty("user_id")
    public long getUserId() {
        return user != null ? user.getUser_id() : 0;
    }

}
