package cs.project.evolt.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CarBrand")
public class CarBrand {
    @Id
    @Column(name="brand_id", unique=true)
    private long brand_id;

    private String brand_name;

    // !! generate constructor, getter, setter
    // OnetoMany with CarModel 1 brand has multiple models
    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarModel> carModel;
}
