package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name="Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id", unique=true)
    private long review_id;

    @Column(name="comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-reference") // มีหลาย reference ต้องตั้งชื่อ
    private User user;


    @Column(name="create_date")
    private LocalDateTime create_date;

    @JsonProperty("station_id")
    public long getStationId() {
        return station != null ? station.getStationId() : 0;
    }

    @JsonProperty("user_id")
    public long getUserId() {
        return user != null ? user.getUserId() : 0;
    }

    @JsonProperty("username")
    public String getUsername() {
        return user != null ? user.getUsername() : null;
    }


}
