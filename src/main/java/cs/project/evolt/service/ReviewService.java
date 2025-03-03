package cs.project.evolt.service;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository userReviewRepository;

    public List<Reviews> getAllReviews() {
        return userReviewRepository.findAll();
    }

    public Optional<Reviews> getReviewById(Long id) {
        return userReviewRepository.findById(id);
    }

    public Reviews saveReview(Reviews review) {
        return userReviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        userReviewRepository.deleteById(id);
    }
}
