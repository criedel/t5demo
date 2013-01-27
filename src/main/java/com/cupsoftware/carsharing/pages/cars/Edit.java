package com.cupsoftware.carsharing.pages.cars;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.cupsoftware.carsharing.model.Car;

public class Edit {

    @Inject
    private EntityManager em;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @InjectComponent
    private Zone carsZone;

    @InjectComponent
    private BeanEditForm addCar;

    @Property
    private List<Car> cars;

    @Property
    private Car car;

    @SuppressWarnings("unchecked")
    void setupRender() {

        cars = em.createQuery("from Car").getResultList();
    }

    @OnEvent(component = "AddCar", value = EventConstants.VALIDATE)
    void validateCarFormSubmission() {

        if (addCar.isValid() && em.createQuery("from Car where name=:name")
                                  .setParameter("name", car.getName())
                                  .getResultList()
                                  .size() > 0) {

            addCar.recordError("Car name is not available.");
        }
    }

    @CommitAfter
    void onSuccessFromAddCar() {

        em.persist(car);
    }

    void onSubmitFromAddCar() {

        // will be called after either success or failure event has been processed.
        ajaxResponseRenderer.addRender(carsZone);
        setupRender();
    }

}
