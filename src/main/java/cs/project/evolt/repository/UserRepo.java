package cs.project.evolt.repository;

import cs.project.evolt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    //boolean checkExistByEmail(String email);

}
