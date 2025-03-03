package cs.project.evolt.service;
import cs.project.evolt.model.Trip;
import cs.project.evolt.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    // Get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}
