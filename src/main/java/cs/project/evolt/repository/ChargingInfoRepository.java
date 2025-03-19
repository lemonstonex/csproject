package cs.project.evolt.repository;

import cs.project.evolt.model.ChargingInfo;
import cs.project.evolt.model.Plug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChargingInfoRepository extends JpaRepository<ChargingInfo, Long>{
}
