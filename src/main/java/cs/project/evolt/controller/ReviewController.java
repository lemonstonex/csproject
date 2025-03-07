package cs.project.evolt.controller;

import cs.project.evolt.DTO.ReviewDTO;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.ReviewRepository;
import cs.project.evolt.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/list")
    public List<Reviews> getReviews() {
        System.out.println("Fetching all reviews...");
        return reviewService.getAllReviews();
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveReview(@RequestBody ReviewDTO reviewDTO) {
        if (reviewDTO.getStationId() == null || reviewDTO.getUserId() == null) {
            return ResponseEntity.badRequest().body("Station ID or User ID cannot be null");
        }

        try {
            System.out.println("ReviewDTO: " + reviewDTO);
            reviewService.saveReview(reviewDTO.getComment(), reviewDTO.getStationId(), reviewDTO.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED).body("Review saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Deleted successfully");

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long id, @RequestBody Reviews updatedReview) {
        Optional<Reviews> existingReview = reviewRepository.findById(id);

        if (existingReview.isPresent()) {
            Reviews review = existingReview.get();

            review.setComment(updatedReview.getComment());

            reviewRepository.save(review);

            return ResponseEntity.ok("Updated successfully");
        } else {
            return ResponseEntity.notFound().build(); // Return 404
        }

    }
}
