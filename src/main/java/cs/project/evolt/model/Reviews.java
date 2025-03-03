package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="Reviews")
public class Reviews {
    @Id
    @Column(name="review_id", unique=true)
    private long review_id;

    @Column(name="comment")
    private String comment;

    @Column(name="station_id")
    private long station_id;

    @Column(name="user_id")
    private long user_id;

    @Column(name="create_date")
    private Timestamp create_date;
}
