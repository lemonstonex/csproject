package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="CarModel")
public class CarModel {

    @Id
    @Column(name = "model_id", unique = true)
    private long modelId;

    @Column(name = "model_name")
    private String model_name;

    @Column(name = "horsepower")
    private String horsepower;

    @Column(name = "efficiency")
    private Double efficiency;

    @ManyToOne
    @JoinColumn(name="car_brand_id", nullable=false)
    private CarBrand carBrand;


}
