package com.anukriti.cabbookinguber.strategies;

import com.anukriti.cabbookinguber.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}
