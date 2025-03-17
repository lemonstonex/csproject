package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="StationRating")
public class StationRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rating_id", unique=true)
    private long rating_id;

    @Column(name = "rating", nullable = false)
    private double rating; // full score of 5 -- with 0.0, 0.5, 1.0, .... , 5.0

    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-reference") // มีหลาย reference ต้องตั้งชื่อ
    private User user;

    @JsonProperty("user_id")
    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }

    @JsonProperty("user_id")
    public void setUserId(Long userId) {
        if (user == null) {
            user = new User();
        }
        user.setUserId(userId);
    }
}
