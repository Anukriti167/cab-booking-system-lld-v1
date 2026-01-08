package com.anukriti.cabbookinguber.database;

import com.anukriti.cabbookinguber.exceptions.RiderAlreadyExistsException;
import com.anukriti.cabbookinguber.exceptions.RiderNotFoundException;
import com.anukriti.cabbookinguber.model.Rider;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class RidersManager {
    Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull final Rider newRider){
        if(riders.containsKey(newRider)){
            throw new RiderAlreadyExistsException();
        }
        riders.put(newRider.getRiderId(), newRider);
    }

    public Rider getRider(@NonNull final String riderId){
        if(!riders.containsKey(riderId)){
            throw new RiderNotFoundException();
        }
        return riders.get(riderId);
    }
}
