package cs.project.evolt.service;

import cs.project.evolt.DTO.ChargingInfoDTO;
import cs.project.evolt.DTO.ResultDTO;
import cs.project.evolt.DTO.TripRequest;
import cs.project.evolt.model.*;
import cs.project.evolt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private ChargingInfoRepository chargingInfoRepository;

    public List<Result> getAllResult() {
        return resultRepository.findAll();
    }

    public ResultService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Result createResult(ResultDTO resultDTO) {
        // เรียก model รถก่อน
        CarModel carModel = carModelRepository.findById(resultDTO.getModelId())
                .orElseThrow(() -> new RuntimeException("car model not found, try again"));

        Result result = new Result();
        result.setCarModel(carModel);
        result.setTotalChargingTime(0.0); // default ค่าเป็น 0.0 เพื่อเอาไปคิดต่อ
        // เซฟ result ใหญ่ก่อน
        Result savedResult = resultRepository.save(result);

        final double[] totalChargingTime = {0.0}; // total_charging_time ชี้ไปที่ array ที่มีค่าเริ่มต้น 0.0

        // เรียก chargingInfoList
        List<ChargingInfo> chargingInfos = resultDTO.getChargingInfoList().stream().map(chargingInfoDTO -> {


            /*** คำนวณ total charging_time จากการสะสมค่า charging time
             *  ซึ่งเป็นค่าที่เปลี่ยนไปตลอด = ต้องใช้ array -- ถึงประกาศเป็น final ก็เปลี่ยนค่าได้
             *  array ref เปลี่ยนไม่ได้ แต่ค่าใน array เปลี่ยนได้
             *
             */
            // สูตรของ chargingTime
            Double chargingTime = null;
            if (chargingInfoDTO.getEnergyUsedWhenCharge() != null) {
                double energyUsed = chargingInfoDTO.getEnergyUsedWhenCharge() / 100.0;
                double value = Math.min(22, carModel.getOn_board_charger());
                double batterySize = carModel.getBattery_size();
                chargingTime = (energyUsed * batterySize) / value;

                chargingTime = roundToOneDecimalPlace(chargingTime);
            }

            if (chargingTime != null) {
                totalChargingTime[0] += chargingTime;
            }

            Station station = stationRepository.findById(chargingInfoDTO.getStationId())
                    .orElseThrow(() -> new RuntimeException("station not found"));

            ChargingInfo chargingInfo = new ChargingInfo();
            chargingInfo.setChargingTime(null);
            chargingInfo.setStationName(station.getStation_name());
            chargingInfo.setLat(station.getLat());
            chargingInfo.setLng(station.getLongitude());
            chargingInfo.setStationId(chargingInfoDTO.getStationId());
            chargingInfo.setEnergyUsedWhenCharge(chargingInfoDTO.getEnergyUsedWhenCharge());
            chargingInfo.setChargingTime(chargingTime);
            chargingInfo.setPlug_id(chargingInfoDTO.getPlugId());

            chargingInfo.setResult(savedResult);

            return chargingInfo;


        }).collect(Collectors.toList());

        chargingInfoRepository.saveAll(chargingInfos);
        savedResult.setTotalChargingTime(totalChargingTime[0]);

        return savedResult;

    }

    public Result getResultById(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("result not found"));
        return result;

    }

    public static double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

}
