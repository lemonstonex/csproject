package cs.project.evolt.service;

import cs.project.evolt.model.Bookmark;
import cs.project.evolt.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository userBookmarkRepository;

    // Get all bookmarks
    public List<Bookmark> getAllBookmarks() {
        return userBookmarkRepository.findAll();
    }

    public Bookmark saveBookmark(Bookmark bookmark) {
        return userBookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long userId) {
        userBookmarkRepository.deleteById(userId);
    }
}
