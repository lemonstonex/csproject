package cs.project.evolt.service;
import cs.project.evolt.model.Route;
import cs.project.evolt.repository.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    // Get all routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }


}
