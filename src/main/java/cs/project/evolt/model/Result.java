package cs.project.evolt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name="Result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id", unique = true)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel;

    @Column(name = "total_charging_time")
    private Double totalChargingTime; // in hours


    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ChargingInfo> chargingInfoList = new ArrayList<>();
}
