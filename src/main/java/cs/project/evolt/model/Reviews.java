package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

    @Column(name="station_id")
    private long station_id;

    @Column(name="user_id")
    private long user_id;

    @Column(name="create_date")
    private Timestamp create_date;
}
