package cs.project.evolt.controller;

import cs.project.evolt.DTO.BookmarkRequest;
import cs.project.evolt.DTO.ReviewDTO;
import cs.project.evolt.model.Bookmark;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/list")
    public List<Bookmark> getBookmark() {
        System.out.println("Fetching all bookmark...");
        return bookmarkService.getAllBookmarks();
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.saveBookmark(bookmarkRequest.getBookmark_id(),bookmarkRequest.getStation_id(), bookmarkRequest.getUser_id());
        return ResponseEntity.ok("Bookmark saved successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("id") Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.ok("Deleted successfully");

    }
}
