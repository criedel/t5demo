package com.cupsoftware.carsharing.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.tapestry5.beaneditor.ReorderProperties;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

@Entity
@ReorderProperties("name,carModel")
public class Car extends BaseEntity {

    private static final long serialVersionUID = 4597051850788379830L;

    @Column(nullable = false, unique = true, length = 100)
    @Validate("required,maxlength=100")
    private String name;

    @Column(nullable = false)
    @Validate("required")
    private CarModel carModel;

    @Inject
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

    @Override
    public String toString() {

        return String.format("%s %s", carModel.toString(), name);
    }
}
