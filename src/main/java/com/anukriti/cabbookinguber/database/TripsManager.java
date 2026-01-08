package com.anukriti.cabbookinguber.database;

import com.anukriti.cabbookinguber.exceptions.NoCabsAvailableException;
import com.anukriti.cabbookinguber.exceptions.TripNotFoundException;
import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import com.anukriti.cabbookinguber.model.Rider;
import com.anukriti.cabbookinguber.model.Trip;
import com.anukriti.cabbookinguber.strategies.CabMatchingStrategy;
import com.anukriti.cabbookinguber.strategies.PricingStrategy;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripsManager {
    private static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    private Map<String, List<Trip>> trips = new HashMap<>();

    private CabsManager cabsManager;
    private RidersManager ridersManager;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;

    public TripsManager(CabsManager cabsManager, RidersManager ridersManager, CabMatchingStrategy cabMatchingStrategy, PricingStrategy pricingStrategy){
        this.cabsManager = cabsManager;
        this.ridersManager = ridersManager;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(@NonNull final Rider rider,
                           @NonNull final Location fromPoint,
                           @NonNull final Location toPoint){
        final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        final List<Cab> closeByAvailableCabs = new ArrayList<>();

        for(Cab cab: closeByCabs){
            if(cab.getCurrentTrip() == null){
                closeByAvailableCabs.add(cab);
            }
        }

        final Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
        if(selectedCab == null){
            throw new NoCabsAvailableException();
        }

        final Double price = pricingStrategy.findPrice(fromPoint, toPoint);
        final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);

        if(!trips.containsKey(rider.getRiderId())){
            trips.put(rider.getRiderId(), new ArrayList<>());
        }
        trips.get(rider.getRiderId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    public void endTrip(@NonNull final Cab cab) {
        if (cab.getCurrentTrip() == null){
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }

    public List<Trip> tripHistory(Rider rider) {
        return trips.get(rider.getRiderId());
    }
}
