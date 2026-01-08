package com.anukriti.cabbookinguber.strategies;

import com.anukriti.cabbookinguber.model.Cab;
import com.anukriti.cabbookinguber.model.Location;
import com.anukriti.cabbookinguber.model.Rider;

import java.util.List;

public class DefaultPricingStrategy implements PricingStrategy{
    public static final Double PER_KM_RATE = 10.0;

    @Override
    public Double findPrice(Location fromPoint, Location toPoint) {
        return fromPoint.distance(toPoint) * PER_KM_RATE;
    }
}
