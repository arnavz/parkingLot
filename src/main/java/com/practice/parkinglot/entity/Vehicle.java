package com.practice.parkinglot.entity;

import com.practice.parkinglot.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "vehicle_audit")
public class Vehicle extends BaseEntity{

     @Column(name = "reg_no")
     @NotNull
     private String registrationNumber;

     @Column
     @NotNull
     private String color;

     @Column(name = "type")
     @Enumerated(value = EnumType.STRING)
     private VehicleType vehicleType;


     @Temporal(value = TemporalType.TIMESTAMP)
     @Column(name= "in_time")
     private Date inTime;

     @Temporal(value = TemporalType.TIMESTAMP)
     @Column(name= "out_time")
     private Date outTime;

     @OneToOne
     @JoinColumn(name = "spot_id", referencedColumnName = "id")
     private Spot spot;

     public Vehicle(){}
     public Vehicle(String registrationNumber){
          this.registrationNumber = registrationNumber;
     }
     public Vehicle(String registrationNumber, String color, VehicleType vehicleType, Spot spot) {
          this.registrationNumber = registrationNumber;
          this.color = color;
          this.vehicleType = vehicleType;
          this.spot = spot;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Vehicle vehicle = (Vehicle) o;
          return registrationNumber.equals(vehicle.registrationNumber);
     }

     @Override
     public int hashCode() {
          return Objects.hash(registrationNumber);
     }

     public String getRegistrationNumber() {
          return registrationNumber;
     }

     public void setRegistrationNumber(String registrationNumber) {
          this.registrationNumber = registrationNumber;
     }

     public String getColor() {
          return color;
     }

     public void setColor(String color) {
          this.color = color.toLowerCase();
     }

     public VehicleType getVehicleType() {
          return vehicleType;
     }

     public void setVehicleType(VehicleType vehicleType) {
          this.vehicleType = vehicleType;
     }

     public Date getInTime() {
          return inTime;
     }

     public void setInTime(Date inTime) {
          this.inTime = inTime;
     }

     public Date getOutTime() {
          return outTime;
     }

     public void setOutTime(Date outTime) {
          this.outTime = outTime;
     }

     public Spot getSpot() {
          return spot;
     }

     public void setSpot(Spot spot) {
          this.spot = spot;
     }


}
