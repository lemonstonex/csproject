package cs.project.evolt.controller;

import cs.project.evolt.DTO.RatingDTO;
import cs.project.evolt.model.Station;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/rate/{station_id}")
    public ResponseEntity<String> rateStationById(
            @PathVariable long station_id,
            @RequestBody RatingDTO ratingDTO) {

        boolean success = stationService.rateStation(station_id, ratingDTO.getUserId(), ratingDTO.getRating());

        if (success) {
            return ResponseEntity.ok("submitted success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Station or User not found.");
        }
    }



    @PutMapping("/rate/{station_id}")
    public ResponseEntity<String> updateStationRating(
            @PathVariable long station_id,
            @RequestBody RatingDTO ratingDTO) {

        boolean updated = stationService.updateUserRating(station_id, ratingDTO.getUserId(), ratingDTO.getRating());

        if (updated) {
            return ResponseEntity.ok("updated success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Station or user not found.");
        }
    }
}
