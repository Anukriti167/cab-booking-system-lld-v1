package com.anukriti.cabbookinguber.database;

import com.anukriti.cabbookinguber.exceptions.CabAlreadyExistsException;
import com.anukriti.cabbookinguber.exceptions.CabNotFound;
import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabsManager {
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(@NonNull final Cab newCab){
        if(cabs.containsKey(newCab.getId())){
            throw new CabAlreadyExistsException();
        }
        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(@NonNull final String cabId){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFound();
        }
        return cabs.get(cabId);
    }

    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFound();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCabAvailability(@NonNull final String cabId, @NonNull final boolean newAvailability){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFound();
        }
        cabs.get(cabId).setIsAvailability(newAvailability);
    }

    public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance){
        List<Cab> result = new ArrayList<>();
        for(Cab cab: cabs.values()){
            if(cab.isAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance){
                result.add(cab);
            }
        }
        return result;
    }
}
