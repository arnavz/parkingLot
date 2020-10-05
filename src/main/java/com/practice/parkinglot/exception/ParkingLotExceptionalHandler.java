package com.practice.parkinglot.exception;


import com.practice.parkinglot.utils.AppConstants;
import com.practice.parkinglot.utils.ResponseBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ParkingLotExceptionalHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ResponseBO response;

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentExceptions(IllegalArgumentException ex){
        response.setReason(AppConstants.ILLEGAL_ARGUMENTS_PASSED);
        response.setStatus(AppConstants.STATUS_EXPECTATION_FAILED);
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler(ParkingLotException.class)
    public final ResponseEntity<Object> handleCustomExceptions(ParkingLotException ex){
        response.setReason(ex.getReason());
        response.setStatus(ex.getStatus());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
}
