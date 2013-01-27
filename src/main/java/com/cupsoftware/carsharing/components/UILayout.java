package com.cupsoftware.carsharing.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

public class UILayout {

    @Inject
    private Messages messages;

    @Property(write = false)
    @Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
    private String title;

    String defaultTitle() {

        return messages.get("title");
    }
}
