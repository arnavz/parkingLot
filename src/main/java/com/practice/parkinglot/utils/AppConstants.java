package com.practice.parkinglot.utils;

public interface AppConstants {
    // --Commented out by Inspection (02/09/20, 5:01 AM):String STATUS_SUCCESS = "Success";
    String STATUS_EXPECTATION_FAILED = "Expectation Failed!";
    String NO_VEHICLE_FOUND = "No Vehicle parked of requested type in provided slot!";
    String REGISTRATION_NUMBER_NOT_FOUND ="Registration Number not found!";
    String INVALID_VEHICLE_TYPE = "Vehicle type is Invalid!";
    String COLOR_IS_NULL = "Color is Null";
    String VEHICLE_ALREADY_PARKED = "Vehicle is already parked!";
    String REGISTRATION_NUMBER_NOT_UNIQUE = "This registration number belongs to another vehicle type!";
    String VEHICLE_TYPE_NOT_FOUND = "Vehicle type is null or empty!";
    String ILLEGAL_ARGUMENTS_PASSED = "Arguments passed are Illegal";
    String PARKING_FULL = "No vacant spaces for this vehicle type, please try again later!";
}
