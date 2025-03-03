package cs.project.evolt.repository;

import cs.project.evolt.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {



}
