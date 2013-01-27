package com.cupsoftware.carsharing.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "context:css/car.css")
public class UILayout {

    @Inject
    private Messages messages;

    @Property(write = false)
    @Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
    private String title;

    @Property
    private boolean loggedIn;

    String defaultTitle() {

        return messages.get("title");
    }

    void setupRender() {

        loggedIn = false;
    }
}
