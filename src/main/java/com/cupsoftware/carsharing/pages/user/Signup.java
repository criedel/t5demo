package com.cupsoftware.carsharing.pages.user;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.cupsoftware.carsharing.AppUtil;
import com.cupsoftware.carsharing.model.User;

/**
 *
 * @author criedel
 */
public class Signup {

    @Inject
    private Messages messages;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    private EntityManager em;

    @Inject
    private AlertManager alertManager;

    @InjectComponent
    private BeanEditForm signupForm;

    @InjectComponent
    private Zone signupZone;

    @Property
    private User user;

    void onValidateFromSignupForm() {

        if (!StringUtils.equals(this.user.getPassword(), this.user.getPasswordRepeat())) {

            signupForm.recordError(messages.get("passwords-must-match"));
        }

        if (em.createNamedQuery("User.findByName")
              .setParameter("name", user.getName().toLowerCase())
              .getResultList().size() > 0) {

            signupForm.recordError(messages.get("user-name-exists"));
        }

        try {

            user.setPasshash(AppUtil.toMD5Hash(user.getPassword()));

        } catch (NoSuchAlgorithmException e) {

            signupForm.recordError(e.getMessage());
        }

        user.setName(user.getName().toLowerCase());
    }

    @CommitAfter // commits the session and actually persists the entity after the method is executed
    Object onSuccessFromSignupForm() {

        em.persist(user);
        alertManager.success(messages.get("user-saved"));

        return Login.class;
    }

    void onFailureFromSignupForm() {

        alertManager.error(messages.get("user-not-saved"));
        ajaxResponseRenderer.addRender("signupZone", signupZone);
    }
}
