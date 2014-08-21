package com.netbuilder.thejuke.security;



import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.netbuilder.thejuke.util.ConfigProperty;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author blep
 *         Date: 16/02/12
 *         Time: 07:28
 */
public class LoginContextProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private SimpleCallbackHandler callbackHandler;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigFile") String loginConfigFileName,
                                            @ConfigProperty("loginModuleName") String loginModuleName) throws LoginException, URISyntaxException {
    	if(callbackHandler != null) System.out.println("Not null");
        //System.out.println(new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI()).getPath());
        System.out.println(loginConfigFileName);
        System.out.println(loginModuleName);
        //System.setProperty("java.security.auth.login.config", new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI()).getPath());
        
        try {
            return new LoginContext(loginModuleName, callbackHandler);
        } catch (Exception e) {
            System.out.println("ouch!!!");
            e.printStackTrace();
            return null;
        }
    }

}
