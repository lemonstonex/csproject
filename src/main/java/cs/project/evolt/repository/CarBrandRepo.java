package cs.project.evolt.repository;

import cs.project.evolt.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepo extends JpaRepository<CarBrand, String> {


}
