package cs.project.evolt.service;
import cs.project.evolt.DTO.TripRequest;
import cs.project.evolt.model.CarModel;
import cs.project.evolt.model.Distance;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.Trip;
import cs.project.evolt.repository.CarModelRepository;
import cs.project.evolt.repository.DistanceRepository;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    // Get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip createTrip(TripRequest tripRequest) {
        // เรียก model รถก่อน
        CarModel carModel = carModelRepository.findById(tripRequest.getModel_id())
                .orElseThrow(() -> new RuntimeException("car model not found, try again"));

        Trip trip = new Trip();
        trip.setTotal_distance(tripRequest.getTotal_distance());
        trip.setCurrent_battery(tripRequest.getCurrent_battery());
        trip.setUserModel(carModel);

        final Trip savedTrip = tripRepository.save(trip);

        List<Distance> distances = tripRequest.getDistancesList().stream().map(distanceDTO -> {
            Station station = stationRepository.findById(distanceDTO.getStation_id())
                    .orElseThrow(() -> new RuntimeException("station not found, try again"));

            Distance distance = new Distance();
            distance.setTrip(savedTrip);
            distance.setStation(station);
            distance.setDistance(distanceDTO.getDistance());

            return distance;
        }).collect(Collectors.toList());

        distanceRepository.saveAll(distances);

        return savedTrip;
    }

    public Trip getTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("trip not found"));
        trip.setRouteList(null);
        return trip;
    }
}
