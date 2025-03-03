package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Bookmark")
public class Bookmark {
    @Id
    @Column(name="bookmark_id", unique=true)
    private long bookmarkId;

    @Column(name = "station_id")
    private long stationId;

    @Column(name = "user_id")
    private long userId;
}
