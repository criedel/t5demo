package com.cupsoftware.carsharing.pages.user;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.jpa.annotations.CommitAfter;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.cupsoftware.carsharing.AppUtil;
import com.cupsoftware.carsharing.model.User;
import com.cupsoftware.carsharing.pages.Index;


public class Settings {

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
    private BeanEditForm settingsForm;

    @InjectComponent
    private Zone settingsZone;

    @SessionState
    @Property
    private User user;

    Object onActivate() {

        if (!asm.exists(User.class)) {

            return Index.class;
        }

        return null;
    }

    void onValidateFromSettingsForm() throws NoSuchAlgorithmException {

        if (!StringUtils.equals(user.getPassword(), user.getPasswordRepeat())) {

            settingsForm.recordError(messages.get("passwords-must-match"));

        } else {

            user.setPasshash(AppUtil.toMD5Hash(user.getPassword()));
        }
    }

    @CommitAfter
    void onSuccessFromSettingsForm() {

        em.merge(user);
        alertManager.success(messages.get("password-saved"));
        ajaxResponseRenderer.addRender("settingsZone", settingsZone);
    }

    void onFailureFromSettingsForm() {

        ajaxResponseRenderer.addRender("settingsZone", settingsZone);
    }
}
