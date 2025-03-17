package cs.project.evolt.controller;

import cs.project.evolt.DTO.BookmarkRequest;
import cs.project.evolt.model.Bookmark;
import cs.project.evolt.model.Station;
import cs.project.evolt.repository.BookmarkRepository;
import cs.project.evolt.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @GetMapping("/list")
    public List<Bookmark> getBookmark() {
        System.out.println("Fetching all bookmark...");
        return bookmarkService.getAllBookmarks();
    }

    @GetMapping("/user/{userId}")
    public List<Bookmark> getBookmarksByUserID(@PathVariable Long userId) {
        return bookmarkRepository.findByUser_UserId(userId);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.saveBookmark(bookmarkRequest.getBookmark_id(),bookmarkRequest.getStation_id(), bookmarkRequest.getUser_id());
        return ResponseEntity.ok("saved success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("id") Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.ok("Deleted success");

    }



}
