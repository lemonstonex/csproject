package cs.project.evolt.service;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.StationRating;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.StationRatingRepository;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationRatingRepository stationRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    public double getAverageRating(long stationId) {
        Double avgRating = stationRatingRepository.findAverageRatingByStationId(stationId);
        return avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0;
    }

    public int getRatingCount(long stationId) {
        return stationRatingRepository.countByStation_StationId(stationId);
    }


    public boolean rateStation(long stationId, long userId, double rating) {
        System.out.println("Attempting to save rating: " + rating); // Debug log

        Optional<Station> stationOptional = stationRepository.findById(stationId);
        Optional<User> userOptional = userRepository.findById(String.valueOf(userId));

        if (stationOptional.isPresent() && userOptional.isPresent()) {
            Station station = stationOptional.get();
            User user = userOptional.get();

            StationRating stationRating = new StationRating();
            stationRating.setStation(station);
            stationRating.setUser(user);
            stationRating.setRating(rating);

            System.out.println("Value set to entity: " + stationRating.getRating()); // Debug log

            StationRating saved = stationRatingRepository.save(stationRating);

            System.out.println("Value after save: " + saved.getRating()); // Debug log
            return true;
        }

        return false;
    }



    public boolean updateUserRating(long stationId, long userId, double rating) {
        Optional<Station> stationOptional = stationRepository.findById(stationId);
        Optional<User> userOptional = userRepository.findById(String.valueOf(userId));  // หา user กับ station

        if (stationOptional.isPresent() && userOptional.isPresent()) {
            Station station = stationOptional.get();
            User user = userOptional.get();

            Optional<StationRating> existingRating = stationRatingRepository.findByStation_StationIdAndUser_UserId(stationId, userId);

            if (existingRating.isPresent()) {
                StationRating stationRating = existingRating.get();
                stationRating.setRating(rating);
                stationRatingRepository.save(stationRating);
            } else {

                StationRating newRating = new StationRating();
                newRating.setStation(station);
                newRating.setUser(user);
                newRating.setRating(rating);
                stationRatingRepository.save(newRating);
            }

            return true;
        }

        return false;
    }



}
