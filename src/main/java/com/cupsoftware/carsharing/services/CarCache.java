package com.cupsoftware.carsharing.services;

import java.util.Set;

public interface CarCache {

    void addCar(String car);

    Set<String> getCachedCars();
}
