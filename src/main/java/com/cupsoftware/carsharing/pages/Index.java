package com.cupsoftware.carsharing.pages;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.cupsoftware.carsharing.model.Car;

public class Index {

    @Inject
    private EntityManager em;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    private List<Car> cars;

    @Property
    private Car car;

    @SuppressWarnings("unchecked")
    void setupRender() {

        cars = em.createQuery("from Car").getResultList();
    }
}
