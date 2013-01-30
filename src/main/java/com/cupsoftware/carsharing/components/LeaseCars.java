package com.cupsoftware.carsharing.components;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.joda.time.DateTime;

import com.cupsoftware.carsharing.model.Car;
import com.cupsoftware.carsharing.model.Leasing;
import com.cupsoftware.carsharing.model.User;

public class LeaseCars {

    @Inject
    private EntityManager em;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    private AlertManager alertManager;

    @InjectComponent
    private Zone leaseCarZone;

    @SessionState
    private User user;

    @Property
    private List<Car> cars;

    @Property
    private Car car;

    // example of how to use joda time
    @Property
    private DateTime leaseTime;

    @SuppressWarnings("unchecked")
    void setupRender() {

        final List<Car> leasedCars = em.createQuery("select car from Leasing where leasingEnd is null").getResultList();

        if (leasedCars.isEmpty()) {

            cars = em.createQuery("from Car c").getResultList();
            return;
        }

        cars = em.createQuery("from Car c where c not in (:leasedCars)")
                    .setParameter("leasedCars", leasedCars)
                    .getResultList();
    }

    @CommitAfter
    void onLease(final Car car) {

        final Leasing leasing = new Leasing(user, car);

        em.persist(leasing);
        ajaxResponseRenderer.addRender(leaseCarZone);

        alertManager.info(String.format("%s was leased by %s", car.getName(), user.getName()));

        setupRender();
    }

    @CommitAfter
    void onSuccessFromSetLeaseTimeForm() {

        final Leasing leasing = new Leasing(user, car);
        leasing.setLeasingStart(leaseTime.toDate());
        leaseTime = null;

        em.persist(leasing);
        ajaxResponseRenderer.addRender(leaseCarZone);

        alertManager.info(String.format("%s was leased by %s", car.getName(), user.getName()));

        setupRender();
    }
}
