package cs.project.evolt.model;

import cs.project.evolt.common.PlugStatus;
import cs.project.evolt.common.PlugType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Plug")
public class Plug {
    @Id
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
