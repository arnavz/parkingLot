package com.practice.parkinglot.controller;


import com.practice.parkinglot.entity.Vehicle;
import com.practice.parkinglot.enums.VehicleType;
import com.practice.parkinglot.service.ParkingLotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/app/parking/*")
public class ParkingLotController {

    @Autowired
    ParkingLotManager parkingLotManager;

    @RequestMapping("park")
    public ResponseEntity parkVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity(parkingLotManager.park(vehicle), HttpStatus.CREATED);
    }

    @RequestMapping("unpark/{registrationNumber}")
    public ResponseEntity unpark(@PathVariable("registrationNumber") String registrationNumber) {
        return new ResponseEntity(parkingLotManager.unPark(registrationNumber),HttpStatus.ACCEPTED);
    }

    @RequestMapping("info/{type}/{slot}")
    public ResponseEntity getSlotInfoByType(@PathVariable("type")String vehicleType, @PathVariable("slot") int slot){
        return new ResponseEntity(parkingLotManager.getSlotInfoByType(VehicleType.valueOf(vehicleType.toUpperCase()), slot),HttpStatus.ACCEPTED);
    }

    @RequestMapping("info/color/{color}")
    public ResponseEntity getAllVehiclesByColor(@PathVariable("color") String color){
        return new ResponseEntity(parkingLotManager.getVehiclesByColor(color),HttpStatus.ACCEPTED);
    }

    @RequestMapping("info/type/{type}")
    public ResponseEntity getAllVehicleByType(@PathVariable("type") String type){
        return new ResponseEntity(parkingLotManager.getVehiclesByType(VehicleType.valueOf(type.toUpperCase())), HttpStatus.ACCEPTED);
    }

}
