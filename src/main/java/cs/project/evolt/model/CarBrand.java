package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="CarBrand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_id", unique=true)
    private long brandId;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "carBrand")
    @JsonManagedReference
    private List<CarModel> models;


}
