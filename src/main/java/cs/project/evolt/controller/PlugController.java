package cs.project.evolt.controller;

import cs.project.evolt.DTO.PlugRequest;
import cs.project.evolt.model.Plug;
import cs.project.evolt.service.PlugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plugs")
public class PlugController {

    @Autowired
    private PlugService plugService;

    @GetMapping("/list")
    public List<Plug> getPlug() {
        System.out.println("Fetching all plug...");
        return plugService.getAllPlugs();
    }

}
