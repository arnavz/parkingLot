package com.practice.parkinglot.exception;

import com.practice.parkinglot.utils.AppConstants;

public class ParkingLotException extends RuntimeException{
    private String status;
    private String reason;
    public ParkingLotException(){}
    public ParkingLotException(String message){
        super(message);
        this.status = AppConstants.STATUS_EXPECTATION_FAILED;
        this.reason = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
