package cs.project.evolt.service;

import cs.project.evolt.DTO.ChargingInfoDTO;
import cs.project.evolt.DTO.ResultDTO;
import cs.project.evolt.model.*;
import cs.project.evolt.repository.PlugRepository;
import cs.project.evolt.repository.ResultRepository;
import cs.project.evolt.repository.RouteRepository;
import cs.project.evolt.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    public Result processResult(ResultDTO resultDTO) {
        // Find the route based on route_id (you can customize this as per your business logic)

        Route route = routeRepository.findById(resultDTO.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        // Fetch the Trip based on trip_id from ResultDTO
        Trip trip = tripRepository.findById(resultDTO.getTripId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        CarModel carModel = trip.getUserModel();
        Float batterySize = carModel.getBattery_size();
        Float onBoardCharger = carModel.getOn_board_charger();

        // Calculate the missing values like total_charging_time
        double totalChargingTime = calculateTotalChargingTime(resultDTO.getChargingInfoList(), batterySize, onBoardCharger);

        // Create Result entity from DTO
        Result result = new Result();
        result.setRoute(route);
        result.setRemainingBattery(resultDTO.getRemainingBattery());
        result.setTotalChargingTime(totalChargingTime);

        // Map ChargingInfoDTO to ChargingInfo entity
        List<ChargingInfo> chargingInfoList = new ArrayList<>();
        for (ChargingInfoDTO chargingInfoDTO : resultDTO.getChargingInfoList()) {
            ChargingInfo chargingInfo = new ChargingInfo();
            chargingInfo.setStationId(chargingInfoDTO.getStationId());
            chargingInfo.setStationName(chargingInfoDTO.getStationName());
            //chargingInfo.setPlugId(chargingInfoDTO.getPlugId());
            chargingInfo.setRouteResult(result);
            chargingInfoList.add(chargingInfo);
        }

        result.setChargingInfoList(chargingInfoList);

        return result;
    }

    private double calculateTotalChargingTime(List<ChargingInfoDTO> chargingInfoList, Float batterySize, Float onBoardCharger) {
        // Example: Calculate based on the energy used when charging
        double energyUsedWhenCharge = 0;

        // Sum the energy used during charging for all stations
        for (ChargingInfoDTO chargingInfoDTO : chargingInfoList) {
            // You can get energy_used_when_charge from the ChargingInfoDTO
            energyUsedWhenCharge += chargingInfoDTO.getEnergyUsedWhenCharge();  // Make sure this field exists in DTO
        }

        // Calculate charging time using the formula
        return (energyUsedWhenCharge * batterySize) / Math.min(22, onBoardCharger);
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }
}
