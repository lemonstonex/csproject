package cs.project.evolt.controller;

import cs.project.evolt.model.Station;
import cs.project.evolt.model.Trip;
import cs.project.evolt.service.StationService;
import cs.project.evolt.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/list")
    public List<Trip> getTripList(){
        System.out.println("Fetching all trips...");
        return tripService.getAllTrips();
    }
}
