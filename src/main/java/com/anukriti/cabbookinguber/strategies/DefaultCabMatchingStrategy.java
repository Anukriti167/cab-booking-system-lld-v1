package com.anukriti.cabbookinguber.strategies;

import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import com.anukriti.cabbookinguber.model.Rider;
import lombok.NonNull;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy{

    @Override
    public Cab matchCabToRider(@NonNull final Rider rider,
                               @NonNull final List<Cab> candidateCabs,
                               @NonNull final Location fromPoint,
                               @NonNull final Location toPoint) {
        if(candidateCabs.isEmpty()) return null;
        return candidateCabs.get(0);
    }
}
