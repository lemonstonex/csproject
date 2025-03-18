package cs.project.evolt.controller;

import cs.project.evolt.DTO.ResultDTO;
import cs.project.evolt.model.Plug;
import cs.project.evolt.model.Result;
import cs.project.evolt.repository.ResultRepository;
import cs.project.evolt.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ResultService resultService;

    @GetMapping("/list")
    public List<Result> getResult() {
        System.out.println("Fetching all result...");
        return resultService.getAllResult();
    }

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody ResultDTO resultDTO) {

        Result result = resultService.processResult(resultDTO);
        Result savedResult = resultService.saveResult(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResult);
    }
}
