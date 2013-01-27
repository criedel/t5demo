package com.cupsoftware.carsharing.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * This component is an example for having no template.
 *
 * @author criedel
 */
@SupportsInformalParameters
public class Headline {

    @Inject
    private ComponentResources componentResources;

    @Inject
    private Messages messages;

    @Parameter(defaultPrefix = BindingConstants.MESSAGE, required = true)
    private String text;

    String defaultHeadline() {

        return messages.get("default-headline");
    }

//    @Mixin
//    private RenderInformals renderInformals;

    void beginRender(final MarkupWriter writer) {

        final String elementName = componentResources.getElementName("h1");

        writer.element(elementName);

        // the same can be accomplished by applying the mixin "RenderInformals"
        componentResources.renderInformalParameters(writer);

        writer.write(text);
    }

    void afterRender(final MarkupWriter writer) {

        writer.end();
    }

}
