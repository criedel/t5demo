package com.cupsoftware.carsharing.services;

import java.util.Date;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.jpa.EntityManagerSource;
import org.apache.tapestry5.jpa.JpaEntityPackageManager;
import org.apache.tapestry5.jpa.PersistenceUnitConfigurer;
import org.apache.tapestry5.jpa.TapestryPersistenceUnitInfo;
import org.apache.tapestry5.validator.ValidatorMacro;
import org.joda.time.DateTime;

import com.cupsoftware.carsharing.AppSymbols;

public class CarsharingModule {

    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {

        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "v3rys3cur3p4$$phr4s3");
    }

    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration) {

    }

    @Contribute(ValidatorMacro.class)
    public static void combineValidators(MappedConfiguration<String, String> configuration) {

        // Now you can annotate your bean properties with @Validate("password")
        configuration.add("password", "required,minlength=5,maxlength=30");
    }

    @Contribute(EntityManagerSource.class)
    public static void configurePersistenceUnitInfos(final MappedConfiguration<String, PersistenceUnitConfigurer> configuration,
                                                     final @Symbol(AppSymbols.DB_DIALECT) String dialect,
                                                     final @Symbol(AppSymbols.DB_USER) String user,
                                                     final @Symbol(AppSymbols.DB_PASSWORD) String password,
                                                     final @Symbol(AppSymbols.DB_URL) String url,
                                                     final @Symbol(AppSymbols.DB_DRIVER) String driver,
                                                     final @Symbol(AppSymbols.DB_DDL_AUTO_MODE) String ddlAuto) {

        final PersistenceUnitConfigurer configurer = new PersistenceUnitConfigurer() {

            @Override
            public void configure(TapestryPersistenceUnitInfo unitInfo) {

                unitInfo.addProperty("hibernate.connection.url", url).addProperty("hibernate.connection.username", user)
                        .addProperty("hibernate.connection.password", password).addProperty("hibernate.dialect", dialect)
                        .addProperty("hibernate.connection.driver_class", driver).addProperty("hibernate.hbm2ddl.auto", ddlAuto)
                        .addProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider")
                        .addProperty("hibernate.c3p0.preferredTestQuery", "SELECT 1");
            }
        };

        configuration.add("CarUnit", configurer);
    }

    @Contribute(JpaEntityPackageManager.class)
    public static void providePackages(Configuration<String> configuration) {

        configuration.add("com.cupsoftware.carsharing.model");
    }

    @Contribute(TypeCoercer.class)
    public static void contributeTypeCoercer(final org.apache.tapestry5.ioc.Configuration<CoercionTuple> configuration) {

        // Add support for Joda Time's DateTime
        // DateTime -> Date
        configuration.add(new CoercionTuple<DateTime, Date>(DateTime.class, Date.class, new Coercion<DateTime, Date>() {

            @Override
            public Date coerce(DateTime input) {

                return input.toDate();
            }
        }));

        // Date -> DateTime
        configuration.add(new CoercionTuple<Date, DateTime>(Date.class, DateTime.class, new Coercion<Date, DateTime>() {

            @Override
            public DateTime coerce(Date input) {

                return new DateTime(input.getTime());
            }
        }));
    }
}
