package cs.project.evolt.repository;

import cs.project.evolt.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
}
