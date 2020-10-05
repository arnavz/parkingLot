package com.practice.parkinglot.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.parkinglot.exception.ParkingLotException;

import java.io.IOException;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T serialize(String s, Class<T> clazz) {
        if ((s.isEmpty() || null == s))
            throw new ParkingLotException("Parking Lot unavailable");
        try {
            return mapper.readValue(s, clazz);
        } catch (IOException ex) {
            throw new RuntimeException("");
        }
    }
}
