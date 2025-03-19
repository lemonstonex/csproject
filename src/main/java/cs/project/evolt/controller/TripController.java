package cs.project.evolt.controller;

import cs.project.evolt.DTO.TripRequest;
import cs.project.evolt.model.Trip;
import cs.project.evolt.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/list")
    public List<Trip> getTripList() {
        System.out.println("Fetching all trips...");
        return tripService.getAllTrips();
    }

    @PostMapping("/save")
    public Trip createTrip(@RequestBody TripRequest tripRequest) {
        return tripService.createTrip(tripRequest);
    }

    @GetMapping("/{trip_id}")
    public Trip getTripById(@PathVariable Long trip_id) {
        return tripService.getTripById(trip_id);
    }

}
