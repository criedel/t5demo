package com.cupsoftware.carsharing.pages.cars;

import java.util.Date;
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

import com.cupsoftware.carsharing.model.Leasing;
import com.cupsoftware.carsharing.model.User;

public class Overview {

    @Inject
    private AlertManager alertManager;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @InjectComponent
    private Zone leasingEntryZone;

    @SessionState
    private User user;

    @Inject
    private EntityManager em;

    @Property
    private List<Leasing> leasings;

    @Property
    private Leasing leasing;

    @SuppressWarnings("unchecked")
    void setupRender() {

        leasings = em.createQuery("from Leasing where user=:user and leasingEnd is null")
                .setParameter("user", user)
                .getResultList();
    }

    @CommitAfter
    void onActionFromEndLeasing(final Leasing leasing) {

        leasing.setLeasingEnd(new Date());
        em.merge(leasing);

        ajaxResponseRenderer.addRender(leasingEntryZone);
        alertManager.info("Leasing has been ended.");

        setupRender();
    }
}
