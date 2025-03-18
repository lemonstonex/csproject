package cs.project.evolt.controller;

import cs.project.evolt.Response.RouteResponse;
import cs.project.evolt.model.Trip;
import cs.project.evolt.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;


    @GetMapping("/cal/{tripId}")
    public RouteResponse calculateRoutes(@PathVariable Long tripId) {
        return routeService.calculateRoutes(tripId);
    }

    @PostMapping("/save/{tripId}")
    public Trip saveRoute(@PathVariable Long tripId, @RequestBody Map<String, Object> routeListData) {
        // Call the service method to save the routes
        return routeService.saveSelectedRoutes(tripId, routeListData);
    }


//    @PostMapping("/save/{tripId}")
//    public ResponseEntity<Trip> saveSelectedRoute(
//            @PathVariable Long tripId,
//            @RequestBody Map<String, Object> routeData) {
//        Trip updatedTrip = routeService.saveSelectedRoute(tripId, routeData);
//        return ResponseEntity.ok(updatedTrip);
//    }
}