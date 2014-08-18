package com.netbuilder.thejuke.controllers;

import javax.enterprise.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.*;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import com.netbuilder.thejuke.entities.*;
import com.netbuilder.thejuke.util.Loggable;
import java.io.Serializable;

@Named
@SessionScoped
@Loggable
@CatchException
public class UserController {
	
	private Credentials credentials;
	
	private User loggedinUser;
	
	private transient LoginContext loginContext;
	
	
}
