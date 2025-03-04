package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Bookmark")
public class Bookmark {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookmark_id", unique=true)
    private long bookmarkId;

    @Column(name = "station_id")
    private long stationId;

    @Column(name = "user_id")
    private long userId;
}
