package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Reviews")
public class Reviews {
    @Id
    @Column(name="review_id", unique=true)
    private long review_id;
}
