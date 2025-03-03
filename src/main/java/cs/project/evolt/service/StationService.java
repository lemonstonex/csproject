package cs.project.evolt.service;
import cs.project.evolt.model.Station;
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
    private ModelMapper modelMapper;


    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    // String url = "https://evolt-backend-service.dev.evtech.dev/stations/evses";

//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//
//    public Station getStations(String url){
//        return webClientBuilder.build()
//                .get()
//                .uri(url)
//                .retrieve()
//                // retrieve = fetch the data, bodyToMono = convert to an instance
//                .bodyToMono(Station.class)
//                .block();
//    }




}
