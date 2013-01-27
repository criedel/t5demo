package com.cupsoftware.carsharing.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

/**
 * Run the application during development with the system parameter
 * -Dtapestry.execution-mode=development to enable this module.
 *
 * @author criedel
 * @see SymbolConstants#EXECUTION_MODE
 */
public class DevelopmentModule {

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {

        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
    }

}
