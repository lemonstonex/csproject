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

    @Column(name = "horsepower")
    private String horsepower;

    @Column(name = "efficiency")
    private Double efficiency; //energy_consump

    @Column(name = "wtlp")
    private String wtlp;

    @Column(name = "battery_cap")
    private Double battery_cap;

    @ManyToOne
    @JoinColumn(name="car_brand_id", nullable=false)
    @JsonBackReference
    private CarBrand carBrand;


}
