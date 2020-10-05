package com.practice.parkinglot.service;

import com.practice.parkinglot.entity.Spot;
import com.practice.parkinglot.entity.Vehicle;
import com.practice.parkinglot.enums.VehicleType;

import java.util.List;


public interface ParkingLotManager {
    Spot park(Vehicle vehicle);

    Vehicle unPark(String regNo);

    Vehicle getSlotInfoByType(VehicleType vehicleType, int slot);

    List<Vehicle> getVehiclesByColor(String color);

    List<Vehicle> getVehiclesByType(VehicleType type);

}