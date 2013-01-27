package com.cupsoftware.carsharing.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

public class DevelopmentModule {

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {

        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
    }

}
