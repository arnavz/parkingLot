package com.practice.parkinglot.utils;

import com.practice.parkinglot.entity.Spot;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class ParkingSpotComparator implements Comparator<Spot>{

    @Override
    public int compare(Spot s1, Spot s2) {
        if(s1.getSlot()==s2.getSlot())
            return 0;
        else if(s1.getSlot()>s2.getSlot())
            return 1;
        else return -1;
    }


}
