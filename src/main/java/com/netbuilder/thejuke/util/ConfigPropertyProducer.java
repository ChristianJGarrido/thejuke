package com.netbuilder.thejuke.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.Properties;

/**
 * @author blep
 *         Date: 16/02/12
 *         Time: 20:56
 */
public class ConfigPropertyProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static Properties props;

    static {
        props = new Properties();
        //System.out.println(ConfigPropertyProducer.class.getResourceAsStream("/config.properties"));
        try {
        	System.out.println("Here\tHere!\n\n\n\n\n");
            props.setProperty("loginModuleName","SimpleLoginModule");
            props.setProperty("loginConfigFile","/jukedb.login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    @Produces
    @ConfigProperty
    public static String produceConfigProperty(InjectionPoint ip) {
        String key = ip.getAnnotated().getAnnotation(ConfigProperty.class).value();

        return props.getProperty(key);
    }
}
