package com.cupsoftware.carsharing.model;

import java.util.Date;

import javax.persistence.Entity;

import org.apache.tapestry5.ioc.annotations.Inject;

@Entity
public class Leasing extends BaseEntity {

    private static final long serialVersionUID = -8027927956482904633L;

    private User user;

    private Car car;

    private Date leasingStart;

    private Date leasingEnd;

    @Inject
    public Leasing() {

    }

    public Leasing(final User user,
                   final Car car) {

        super();
        this.user = user;
        this.car = car;
        this.leasingStart = new Date();
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Car getCar() {

        return car;
    }

    public void setCar(Car car) {

        this.car = car;
    }

    public Date getLeasingStart() {

        return leasingStart;
    }

    public void setLeasingStart(Date leasingStart) {

        this.leasingStart = leasingStart;
    }

    public Date getLeasingEnd() {

        return leasingEnd;
    }

    public void setLeasingEnd(Date leasingEnd) {

        this.leasingEnd = leasingEnd;
    }

    @Override
    public String toString() {

        return String.format("%s leased '%s' at %s", user, car, leasingStart);
    }
}
