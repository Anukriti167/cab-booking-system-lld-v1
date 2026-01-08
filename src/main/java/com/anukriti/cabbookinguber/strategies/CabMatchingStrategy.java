package com.anukriti.cabbookinguber.strategies;

import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import com.anukriti.cabbookinguber.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
