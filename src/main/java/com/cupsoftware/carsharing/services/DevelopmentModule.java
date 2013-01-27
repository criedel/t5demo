package com.cupsoftware.carsharing.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

import com.cupsoftware.carsharing.AppSymbols;

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

        configuration.add(AppSymbols.DB_DIALECT, "org.hibernate.dialect.HSQLDialect");
        configuration.add(AppSymbols.DB_USER, "sa");
        configuration.add(AppSymbols.DB_PASSWORD, "");
        configuration.add(AppSymbols.DB_DRIVER, "org.hsqldb.jdbcDriver");
        configuration.add(AppSymbols.DB_URL, "jdbc:hsqldb:mem:maildb");
        configuration.add(AppSymbols.DB_DDL_AUTO_MODE, "update");
    }

}
