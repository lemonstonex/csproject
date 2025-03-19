package cs.project.evolt.service;

import cs.project.evolt.Response.RouteResponse;
import cs.project.evolt.model.*;
import cs.project.evolt.repository.RouteRepository;
import cs.project.evolt.repository.StationRepository;
import cs.project.evolt.repository.TripRepository;
import cs.project.evolt.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private StationRepository stationRepository;

    /**
     * Calculate optimal routes based on current battery level and station distances
     * @param tripId the ID of the trip to calculate routes for
     * @return RouteResponseDTO containing candidate routes
     */
    public RouteResponse calculateRoutes(Long tripId) {
        // Fetch the trip with all associated data
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));

        float totalDistance = trip.getTotal_distance();
        Integer currentBattery = trip.getCurrent_battery();
        List<Distance> distanceList = trip.getDistancesList();

        // Get car model specifications from the database
        CarModel carModel = trip.getUserModel();

        // Use specifications from car model
        double batterySize = carModel.getBattery_size();  // kWh
        double energyConsumption = carModel.getEnergy_consump();  // kWh/km
        double maxRange = carModel.getMax_range();  // km

        List<Map<String, Object>> candidateRoutes = new ArrayList<>();

        // Calculate battery percentage required for the total distance
        int batteryRequiredForTotalDistance = customRound((totalDistance * energyConsumption / batterySize) * 100);
        System.out.println("Battery required: " + batteryRequiredForTotalDistance + ", Current: " + currentBattery);

        // Check if the car can reach the destination directly
        if (currentBattery - batteryRequiredForTotalDistance >= 20) {
            RouteResponse response = new RouteResponse();
            response.setTripId(tripId);
            response.setCurrentBattery(currentBattery);

            List<Map<String, Object>> candidateRoutesData = new ArrayList<>();
            Map<String, Object> directRoute = new HashMap<>();
            directRoute.put("route_id", 0L);
            directRoute.put("remaining_battery_at_destination", currentBattery - batteryRequiredForTotalDistance);

            List<Map<String, Object>> chargingInfoList = new ArrayList<>();
            directRoute.put("chargingInfoList", chargingInfoList);
            candidateRoutesData.add(directRoute);

            response.setRouteList(candidateRoutesData);
            return response;
        }

        List<Map<String, Object>> candidateRoutesData = new ArrayList<>();

        for (Distance station : distanceList) {
            Long stationId = station.getStationId();
            Float stationDistance = station.getDistance();

            // Calculate the battery percentage required to reach this station
            int batteryRequiredForStation = customRound((stationDistance * energyConsumption / batterySize) * 100);

            // Check if the car can reach the station with the current battery
            System.out.println("Station ID and distance: " + stationId + ", " + stationDistance);
            System.out.println("Current battery - battery required: " + currentBattery + " - " + batteryRequiredForStation);

            if (currentBattery - batteryRequiredForStation >= 10) {
                System.out.println("From start go to " + stationId);

                double batteryAtStation = currentBattery - batteryRequiredForStation;
                System.out.println("Battery at station: " + batteryAtStation);

                // Determine the battery percentage required to reach the destination from the station
                int batteryRequiredForRemainingDistance = customRound(((totalDistance - stationDistance) * energyConsumption / batterySize) * 100 * 1.2);

                double chargeTo = Math.min(80, batteryAtStation + (batteryRequiredForRemainingDistance + 20));

                int energyUsedWhenCharge = customRound(chargeTo - batteryAtStation);

                Station stationDetails = stationRepository.findById(stationId)
                        .orElseThrow(() -> new RuntimeException("Station not found with id: " + stationId));

                Map<String, Object> route = new HashMap<>();
                route.put("route_id", stationId);

                List<Map<String, Object>> chargingInfoList = new ArrayList<>();
                Map<String, Object> stationInfo = new HashMap<>();
                stationInfo.put("stationName", stationDetails.getStation_name());
                stationInfo.put("stationId", stationId);
                stationInfo.put("battery_at_station", batteryAtStation);
                stationInfo.put("charge_to", chargeTo);
                stationInfo.put("energy_used_when_charge", energyUsedWhenCharge);
                chargingInfoList.add(stationInfo);

                route.put("chargingInfoList", chargingInfoList);

                // If still can't reach destination, check the next station
                while (chargeTo <= 80 && chargeTo < batteryRequiredForRemainingDistance) {
                    boolean nextStationFound = false;

                    for (Distance nextStation : distanceList) {
                        // Ensure next station (further station)
                        if (!nextStation.getStationId().equals(stationId) && nextStation.getDistance() > stationDistance) {
                            System.out.println("From start go to next station: " + stationId);

                            float nextStationDistance = nextStation.getDistance() - stationDistance;

                            // Calculate the battery percentage required to reach the next station
                            int batteryRequiredForNextStation = customRound((nextStationDistance * energyConsumption / batterySize) * 100 * 1.2);

                            chargeTo = Math.min(80, batteryAtStation + (batteryRequiredForNextStation + 20));

                            // Check if the car can reach the next station after charging
                            if (chargeTo - batteryRequiredForNextStation >= 10) {
                                stationDistance = nextStation.getDistance();
                                double batteryUsed = batteryRequiredForNextStation;
                                batteryAtStation = chargeTo - batteryUsed;
                                chargeTo = Math.min(80, batteryAtStation + (batteryRequiredForRemainingDistance + 20));
                                energyUsedWhenCharge = customRound(chargeTo - batteryAtStation);

                                Map<String, Object> nextStationInfo = new HashMap<>();
                                nextStationInfo.put("stationName", "Station " + nextStation.getStationId());
                                nextStationInfo.put("stationId", nextStation.getStationId());
                                nextStationInfo.put("battery_at_station", batteryAtStation);
                                nextStationInfo.put("charge_to", chargeTo);
                                nextStationInfo.put("energy_used_when_charge", energyUsedWhenCharge + " %");

                                chargingInfoList.add(nextStationInfo);

                                nextStationFound = true;
                                break;
                            }
                        }
                    }

                    if (!nextStationFound) {
                        break;  // No more stations can be reached
                    }
                }

                // After reaching the destination, calculate the remaining battery
                double remainingBattery = chargeTo - batteryRequiredForRemainingDistance;
                System.out.println("Remaining battery: " + remainingBattery + ", charge to: " + chargeTo +
                        ", battery required for remaining distance: " + batteryRequiredForRemainingDistance);

                // Add condition to discard routes if the remaining battery is less than 20%
                if (remainingBattery >= 20) {
                    Map<String, Object> candidateRoute = new HashMap<>();
                    candidateRoute.put("route_id", route.get("route_id"));
                    candidateRoute.put("chargingInfoList", route.get("chargingInfoList"));
                    candidateRoute.put("remaining_battery_at_destination", Math.max(0, remainingBattery));

                    candidateRoutesData.add(candidateRoute);
                }
            }
        }

        // Prepare the response
        RouteResponse response = new RouteResponse();
        response.setTripId(tripId);
        response.setCurrentBattery(currentBattery);
        response.setRouteList(candidateRoutesData);

        return response;
    }


    public Trip saveSelectedRoutes(Long tripId, Map<String, Object> routeListData) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));

        // Extract routeList from the provided data
        List<Map<String, Object>> routeList = (List<Map<String, Object>>) routeListData.get("routeList");

        // List to hold all saved Route entities
        List<Route> savedRoutes = new ArrayList<>();

        for (Map<String, Object> routeData : routeList) {
            // Create a new Route entity
            Route route = new Route();
            route.setTrip(trip); // Set the trip associated with this route
            route.setRoute_id((Long) routeData.get("route_id")); // Set route_id

            // Extract remaining battery from routeData
            Double remainingBattery = (Double) routeData.get("remaining_battery_at_destination");
            route.setRemaining_battery(remainingBattery.intValue());

            // Extract charging info from the routeData
            List<Map<String, Object>> chargingInfoList = (List<Map<String, Object>>) routeData.get("chargingInfoList");

            // List to hold ChargingInfo entities
            List<ChargingInfo> chargingInfoEntities = new ArrayList<>();

            // Iterate over each charging info entry and save them
            for (Map<String, Object> stationInfo : chargingInfoList) {
                ChargingInfo chargingInfo = new ChargingInfo();
                chargingInfo.setRoute(route); // Set the associated route
                chargingInfo.setStationName((String) stationInfo.get("stationName"));
                chargingInfo.setStationId(((Number) stationInfo.get("stationId")).longValue());
                chargingInfo.setBatteryAtStation((int) ((Number) stationInfo.get("battery_at_station")).doubleValue());
                chargingInfo.setChargeTo((int) ((Number) stationInfo.get("charge_to")).doubleValue());

                // Handle energy used when charging (remove the "%" sign)
                int energyUsedWhenCharge = (int) stationInfo.get("energy_used_when_charge");
                chargingInfo.setEnergyUsedWhenCharge(energyUsedWhenCharge);

                chargingInfoEntities.add(chargingInfo); // Add to list
            }

            route.setChargingList(chargingInfoEntities); // Set charging info list to route
            routeRepository.save(route); // Save the route
            savedRoutes.add(route); // Add to savedRoutes list
        }

        // Add the saved routes to the trip's route list
        trip.setRouteList(savedRoutes);
        return tripRepository.save(trip); // Save the updated trip with the new routes
    }


    private int customRound(double value) {
        int intValue = (int) value; // Extract integer part
        return (value % 1 >= 0.5) ? intValue + 1 : intValue;
    }
}