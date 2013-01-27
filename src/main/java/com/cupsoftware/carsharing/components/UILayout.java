package com.cupsoftware.carsharing.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.cupsoftware.carsharing.model.User;

@Import(stylesheet = "context:css/car.css")
public class UILayout {

    @Inject
    private Messages messages;

    @SessionState
    private User user;

    private boolean userExists;

    @Property(write = false)
    @Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
    private String title;

    @Property
    private boolean loggedIn;

    String defaultTitle() {

        return messages.get("title");
    }

    void setupRender() {

        loggedIn = userExists;
    }
}
