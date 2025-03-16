package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trip_id", unique=true)
    private long trip_id;

    @Column(name="total_distance")
    private float total_distance; // start -> dest

    @Column(name="current_battery")
    private Integer current_battery;

    @OneToOne
    @JoinColumn(name="model_id", nullable=false)
    @JsonBackReference("model-reference") // มีหลาย reference ต้องตั้งชื่อ
    private CarModel userModel;

    @OneToMany(mappedBy = "trip")
    @JsonManagedReference("trip-reference") // มีหลาย reference ต้องตั้งชื่อ
    private List<Route> routeList;

    @OneToMany(mappedBy = "trip",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference("trip-reference")
    private List<Distance> distancesList;

    @Transient
    public Long getModelId() {
        return userModel != null ? userModel.getModelId() : null;
    }


}
