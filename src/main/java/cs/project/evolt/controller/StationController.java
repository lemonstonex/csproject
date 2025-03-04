package cs.project.evolt.controller;

import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationRepository stationRepository;
    @GetMapping("/list")
    public List<Station> getStations(){
        System.out.println("Fetching all stations...");
        return stationService.getAllStations();
    }


    @GetMapping("/{station_id}")
    public ResponseEntity<Station> getStationById(@PathVariable long station_id) {
        return stationService.getStationById(station_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
