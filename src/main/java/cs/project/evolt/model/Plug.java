package cs.project.evolt.model;

import cs.project.evolt.common.PlugStatus;
import cs.project.evolt.common.PlugType;
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

    @Column(name="station_id")
    private long station_id; // one station can have multiple plugs

    @Column(name="status")
    private PlugStatus status;

    @Column(name="plug_type")
    private String plug_type; // AC or DC

    @Column(name="pricing")
    private String pricing;

    @Column(name="maxKwh")
    private float maxKwh; // power output

}
