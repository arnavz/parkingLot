package com.practice.parkinglot.repositories;

import com.practice.parkinglot.entity.Vehicle;
import com.practice.parkinglot.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    Vehicle findByRegistrationNumber(String registrationNumber);
    List<Vehicle> findAllByColor(String color);
    List<Vehicle> findAllByVehicleType(VehicleType type);
    Vehicle findTop1ByRegistrationNumberOrderByInTimeDesc(String registrationNumber);
}
