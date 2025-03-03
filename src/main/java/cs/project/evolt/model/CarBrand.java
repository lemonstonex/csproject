package cs.project.evolt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="CarBrand")
public class CarBrand {
    @Id
    @Column(name="brand_id", unique=true)
    private long brandId;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "carBrand")
    private List<CarModel> models;
}
