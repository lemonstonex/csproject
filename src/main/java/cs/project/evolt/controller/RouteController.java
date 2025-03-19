package cs.project.evolt.controller;

import cs.project.evolt.Response.RouteResponse;
import cs.project.evolt.model.Trip;
import cs.project.evolt.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
        return routeService.saveSelectedRoutes(tripId, routeListData);
    }

}