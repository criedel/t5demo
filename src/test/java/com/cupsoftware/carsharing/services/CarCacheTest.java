package com.cupsoftware.carsharing.services;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.tapestry5.test.TapestryTestCase;
import org.testng.annotations.Test;

public class CarCacheTest extends TapestryTestCase {

    @Test
    public void add_car() {

        final Registry registry = RegistryBuilder.buildAndStartupRegistry(CarsharingModule.class, DevelopmentModule.class);

        final CarCache cache = registry.getService(CarCache.class);

        cache.addCar("BMW");

        assertTrue(cache.getCachedCars().contains("BMW"));
    }
}
