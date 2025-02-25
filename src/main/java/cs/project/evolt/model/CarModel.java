package cs.project.evolt.model;

import jakarta.persistence.*;

@Entity
@Table(name="CarModel")
public class CarModel {

    @Id
    @Column(name = "model_id", unique = true)
    private long model_id;
    private String model_name;
    private String horsepower;
    private Double efficiency;


    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false) // Inverse Side
    private CarBrand carBrand;


}
