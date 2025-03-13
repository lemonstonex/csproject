package cs.project.evolt.service;
import cs.project.evolt.model.Station;
import cs.project.evolt.repository.StationRatingRepository;
import cs.project.evolt.repository.StationRepository;
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


}
