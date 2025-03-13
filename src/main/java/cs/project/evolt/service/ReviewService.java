package cs.project.evolt.service;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.ReviewRepository;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository userReviewRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private UserRepository userRepository;
    public List<Reviews> getAllReviews() {
        return userReviewRepository.findAll();
    }

    public void saveReview(String comment, Long stationId, Long userId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found for id " + stationId));

        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found for id " + userId));
        Reviews review = new Reviews();
        review.setComment(comment);
        review.setStation(station);
        review.setUser(user);
        review.setCreate_date(LocalDateTime.now());

        userReviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        userReviewRepository.deleteById(id);
    }
}
