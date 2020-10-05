package com.practice.parkinglot.service.impl;

import com.practice.parkinglot.entity.Spot;
import com.practice.parkinglot.entity.Vehicle;
import com.practice.parkinglot.enums.VehicleType;
import com.practice.parkinglot.exception.ParkingLotException;
import com.practice.parkinglot.repositories.VehicleRepo;
import com.practice.parkinglot.service.ParkingLot;
import com.practice.parkinglot.utils.AppConstants;
import com.practice.parkinglot.service.ParkingLotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingLotManagerImpl implements ParkingLotManager {

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    ParkingLot parkingLot;

    @Override
    public Spot park(Vehicle vehicle) {
        if (StringUtils.isEmpty(vehicle.getRegistrationNumber())) {
            throw new ParkingLotException(AppConstants.REGISTRATION_NUMBER_NOT_FOUND);
        }
        if (null == vehicle.getVehicleType()) {
            throw new ParkingLotException(AppConstants.INVALID_VEHICLE_TYPE);
        }
        if (StringUtils.isEmpty(vehicle.getColor())) {
            throw new ParkingLotException(AppConstants.COLOR_IS_NULL);
        }
        Vehicle v = vehicleRepo.findByRegistrationNumber(vehicle.getRegistrationNumber());
        if(Objects.nonNull(v)){
            if(!v.getVehicleType().equals(vehicle.getVehicleType()))
            throw new ParkingLotException(AppConstants.REGISTRATION_NUMBER_NOT_UNIQUE);
            else throw new ParkingLotException(AppConstants.VEHICLE_ALREADY_PARKED);
        }

        if(v == null || v.getOutTime().before(new Date())){
            vehicle.setInTime(new Date());
            return parkingLot.park(vehicle);
        }
        throw new ParkingLotException(AppConstants.VEHICLE_ALREADY_PARKED);

    }

    @Override
    public Vehicle unPark(String registrationNumber) {

        if(StringUtils.isEmpty(registrationNumber)){
            throw new ParkingLotException(AppConstants.REGISTRATION_NUMBER_NOT_FOUND);
        }
        Vehicle v = vehicleRepo.findTop1ByRegistrationNumberOrderByInTimeDesc(registrationNumber);
        if(v == null)
            throw new ParkingLotException(AppConstants.NO_VEHICLE_FOUND);
       if(v.getSpot().getVehicle() == null || (!v.getSpot().getVehicle().getRegistrationNumber().equals(registrationNumber)) ){
           throw new ParkingLotException(AppConstants.NO_VEHICLE_FOUND);
       }
        v.setOutTime(new Date());
        parkingLot.unPark(v);
        return v;
    }

    @Override
    public Vehicle getSlotInfoByType(VehicleType vehicleType, int slot) {
        if (StringUtils.isEmpty(vehicleType))
            throw new ParkingLotException(AppConstants.VEHICLE_TYPE_NOT_FOUND);
        Spot spot = parkingLot.getSlotInfoByType(vehicleType, slot);
        if(Objects.isNull(spot)){
            throw new ParkingLotException(AppConstants.NO_VEHICLE_FOUND);
        }
        return spot.getVehicle();
    }

    @Override
    public List<Vehicle> getVehiclesByColor(String color) {
        return parkingLot.getVehiclesByColor(color);
    }

    @Override
    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return parkingLot.getVehiclesByType(type);
    }

}
