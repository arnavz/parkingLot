package com.practice.parkinglot.repositories;

import com.practice.parkinglot.entity.Spot;
import com.practice.parkinglot.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepo extends JpaRepository<Spot, Long> {
	Spot findByVehicleTypeAndSlot(VehicleType type, int slot);
}
