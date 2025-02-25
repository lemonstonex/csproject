package cs.project.evolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Plug")
public class Plug {
    @Id
    @Column(name="plug_id", unique=true)
    private long plug_id;
}
