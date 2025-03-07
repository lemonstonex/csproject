package cs.project.evolt.repository;

import cs.project.evolt.model.Reviews;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {


}
