package com.anukriti.cabbookinguber.model;

import lombok.Getter;

import java.util.Locale;

@Getter
public class Cab {
    private final String id;
    private final String driverName;
    private Trip currentTrip;
    private Location currentLocation;
    private boolean isAvailable;


    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString(){
        return "Cab{" +
                "Id: '" + id + '\'' +
                ", Driver Name: '" + driverName + '\''+
                ", Current Location: '" + currentLocation + '\''+
                ", Is Available: '" + isAvailable + '}';
    }

    public void setCurrentLocation(Location newLocation) {
        this.currentLocation = newLocation;
    }

    public void setIsAvailability(boolean availability){
        this.isAvailable = availability;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }
}
