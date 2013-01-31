package com.cupsoftware.carsharing.pages.user;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.cupsoftware.carsharing.AppUtil;
import com.cupsoftware.carsharing.model.User;
import com.cupsoftware.carsharing.pages.Index;

public class Login {

    @Inject
    private Messages messages;

    @Inject
    private AlertManager alertManager;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    private EntityManager em;

    @Inject
    private ApplicationStateManager asm;

    @InjectComponent
    private BeanEditForm loginForm;

    @InjectComponent
    private Zone loginZone;

    @Property
    private User user;

    void onValidateFromLoginForm() throws NoSuchAlgorithmException {

        @SuppressWarnings("unchecked")
        final List<User> userFromDB = em.createNamedQuery("User.findByNameAndPasshash")
                                         .setParameter("name", user.getName().toLowerCase())
                                         .setParameter("passhash", AppUtil.toMD5Hash(user.getPassword()))
                                         .setMaxResults(1)
                                         .getResultList();

        if (userFromDB.isEmpty()) {

            loginForm.recordError(messages.get("user-not-found"));

        } else if (loginForm.isValid()) {

            asm.set(User.class, userFromDB.get(0));
        }
    }

    Object onSuccessFromLoginForm() {

        return Index.class;
    }

    void onFailureFromLoginForm() {

        ajaxResponseRenderer.addRender("loginZone", loginZone);
    }
}
