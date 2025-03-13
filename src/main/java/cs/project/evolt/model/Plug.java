package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cs.project.evolt.common.PlugStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Plug")
public class Plug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plug_id", unique=true)
    private long plug_id;

    @Column(name="status")
    private PlugStatus status;

    @Column(name="plug_type")
    private String plug_type; // AC or DC

    @Column(name="pricing")
    private String pricing;

    @Column(name="maxKwh")
    private float maxKwh; // power output

    @ManyToOne
    @JoinColumn(name="station_id", nullable=false)
    @JsonBackReference("station-reference") // มีหลาย reference ต้องตั้งชื่อ
    private Station station;

}
