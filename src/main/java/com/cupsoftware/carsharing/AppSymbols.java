package com.cupsoftware.carsharing;

import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.FactoryDefaults;
import org.apache.tapestry5.ioc.services.SymbolSource;

/**
 * Application specific configuration symbols.
 *
 * @author criedel
 *
 * @see SymbolSource
 * @see ApplicationDefaults
 * @see FactoryDefaults
 */
public class AppSymbols {

    public static final String DB_URL = "hibernate.connection.url";

    public static final String DB_USER = "hibernate.connection.username";

    public static final String DB_PASSWORD = "hibernate.connection.password";

    public static final String DB_DIALECT = "hibernate.dialect";

    public static final String DB_DRIVER = "hibernate.connection.driver_class";

    public static final String DB_DDL_AUTO_MODE = "hbm2ddl.auto";

}
