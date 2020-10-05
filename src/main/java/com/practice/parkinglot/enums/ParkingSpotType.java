package com.practice.parkinglot.enums;

public enum ParkingSpotType {
    SMALL("smallSpot"), MEDIUM("mediumSpot"), LARGE("largeSpot");

    private final String spot;

    ParkingSpotType(String spotType) {
        this.spot = spotType;
    }

    public String getSpotType(){
        return spot;
    }
}
