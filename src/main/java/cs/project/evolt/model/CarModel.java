package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="CarModel")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id", unique = true)
    private long modelId;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "on_board_charger")
    private float on_board_charger;

    @Column(name = "max_range")
    private double max_range;

    @Column(name = "energy_consump")
    private double energy_consump;

    @Column(name = "battery_size")
    private float battery_size;

    @ManyToOne
    @JoinColumn(name="car_brand_id", nullable=false)
    @JsonBackReference
    private CarBrand carBrand;


}
