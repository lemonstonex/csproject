package cs.project.evolt.service;

import cs.project.evolt.DTO.BookmarkRequest;
import cs.project.evolt.model.Bookmark;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.BookmarkRepository;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository userBookmarkRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Bookmark> getAllBookmarks() {
        return userBookmarkRepository.findAll();
    }


    public void saveBookmark(Long bookmark_id, Long stationId, Long userId) {
        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found !!!!!!!"));

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setStation(station);

        userBookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long id) {
        userBookmarkRepository.deleteById(id);
    }
}
