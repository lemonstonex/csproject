package cs.project.evolt.repository;

import cs.project.evolt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  User findByEmail(String email);
  User findByUsername(String username);
}
