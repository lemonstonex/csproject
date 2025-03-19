package cs.project.evolt.controller;

import cs.project.evolt.DTO.ResultDTO;
import cs.project.evolt.DTO.TripRequest;
import cs.project.evolt.model.ChargingInfo;
import cs.project.evolt.model.Result;
import cs.project.evolt.model.Trip;
import cs.project.evolt.repository.ResultRepository;
import cs.project.evolt.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/save")
    public Result createResult(@RequestBody ResultDTO resultDTO) {
        return resultService.createResult(resultDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {
        // หา result จาก id
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        // คำนวณ totalchargingtime บวกตามที่มีใน object ทุกครั้งที่ get
        double totalChargingTime = result.getChargingInfoList().stream()
                .filter(ci -> ci.getChargingTime() != null)
                .mapToDouble(ChargingInfo::getChargingTime)
                .sum();

        result.setTotalChargingTime(totalChargingTime);

        return ResponseEntity.ok(result);
    }

}
