package com.cupsoftware.carsharing.model;

import org.apache.tapestry5.beaneditor.ReorderProperties;

@ReorderProperties("name,carModel")
public class Car extends BaseEntity {

    private static final long serialVersionUID = 4597051850788379830L;

    private String name;

    private CarModel carModel;

    public Car() {

    }

    public Car(final String name,
               final CarModel carModel) {

        super();
        this.name = name;
        this.carModel = carModel;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public CarModel getCarModel() {

        return carModel;
    }

    public void setCarModel(CarModel carModel) {

        this.carModel = carModel;
    }

}
