package com.anukriti.cabbookinguber.controllers;

import com.anukriti.cabbookinguber.database.RidersManager;
import com.anukriti.cabbookinguber.database.TripsManager;
import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import com.anukriti.cabbookinguber.model.Rider;
import com.anukriti.cabbookinguber.model.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RidersController {
    private RidersManager ridersManager;
    private TripsManager tripsManager;

    public RidersController(RidersManager ridersManager, TripsManager tripsManager){
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String riderName){
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(final String riderId, final Double sourceX, final Double sourceY, final Double destX, final Double destY){
        tripsManager.createTrip(ridersManager.getRider(riderId), new Location(sourceX, sourceY), new Location(destX, destY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity fetchHistory(final String riderId){
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
