package com.practice.parkinglot.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.practice.parkinglot.entity.Spot;
import com.practice.parkinglot.exception.ParkingLotException;
import com.practice.parkinglot.utils.AppConstants;
import com.practice.parkinglot.utils.ParkingSpotComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.practice.parkinglot.entity.Vehicle;
import com.practice.parkinglot.enums.ParkingSpotType;
import com.practice.parkinglot.enums.VehicleType;
import com.practice.parkinglot.repositories.SpotRepo;
import com.practice.parkinglot.repositories.VehicleRepo;
import com.practice.parkinglot.utils.JsonUtils;

import javax.annotation.PostConstruct;

@Component
public class ParkingLot {

    @Value("${parkinglot}")
    private String parkingLot;

	@Autowired
    SpotRepo spotRepo;

    @Autowired
    VehicleRepo vehicleRepo;
    
    @Autowired
    ParkingSpotComparator parkingSpotComparator;
    

    private TreeSet<Spot> smallSpots;
    private TreeSet<Spot> mediumSpots;
    private TreeSet<Spot> largeSpots;

    @PostConstruct
    public void initializeParkingLot() {
    	smallSpots  = new TreeSet<>(parkingSpotComparator);
        mediumSpots = new TreeSet<>(parkingSpotComparator);
        largeSpots  = new TreeSet<>(parkingSpotComparator);
        Map<String, Integer> parkingSlotsMap = JsonUtils.serialize(parkingLot, Map.class);
        int small = parkingSlotsMap.get(ParkingSpotType.SMALL.getSpotType());
        int medium = parkingSlotsMap.get(ParkingSpotType.MEDIUM.getSpotType());
        int large = parkingSlotsMap.get(ParkingSpotType.LARGE.getSpotType());

        for (int i = 1; i <= small; i++)
            addSpotsToParking(smallSpots, i, VehicleType.BIKE);
        for (int i = 1; i <= medium; i++)
            addSpotsToParking(mediumSpots, i, VehicleType.CAR);
        for (int i = 1; i <= large;  i++)
            addSpotsToParking(largeSpots, i, VehicleType.TRUCK);
    }

    private void addSpotsToParking(TreeSet<Spot> spot, int slot, VehicleType vehicleType) {
        spot.add(new Spot(vehicleType,slot));
    }
    private void addSpotToParking(Spot spot) {
        switch (spot.getVehicleType()) {
            case BIKE:  smallSpots.add(spot);  break;
            case CAR: mediumSpots.add(spot); break;
            case TRUCK:  largeSpots.add(spot);  break;
        }
    }

    public Spot park(Vehicle vehicle){
        Spot spot = null;
        spot = getSpotByVehicleType(vehicle.getVehicleType());
        if(spot == null)
            throw new ParkingLotException(AppConstants.PARKING_FULL);
        spot.setVehicle(vehicle);
        spot.setVehicleType(vehicle.getVehicleType());
        vehicle = vehicleRepo.save(vehicle);
        vehicle.setSpot(spot);
        return spotRepo.save(spot);
    }
    public Vehicle unPark(Vehicle vehicle) {
        Spot spot = vehicle.getSpot();
        spot.setVehicle(null);
        spotRepo.save(spot);
        addSpotToParking(spot);
        return vehicle;
    }



    private Spot getSpotByVehicleType(VehicleType vehicleType) {
        switch (vehicleType) {
            case TRUCK:
                synchronized (largeSpots){
                    return largeSpots.pollFirst();
                }
            case CAR:
                synchronized (mediumSpots){
                    return mediumSpots.pollFirst();
                }
            case BIKE:
                synchronized (smallSpots){
                    return smallSpots.pollFirst();
                }
        }
            return null;
        }

    public Spot getSlotInfoByType(VehicleType type, int slot) {
        return spotRepo.findByVehicleTypeAndSlot(type, slot);
    }
    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleRepo.findAllByColor(color);
    }
    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return vehicleRepo.findAllByVehicleType(type);
    }
}

