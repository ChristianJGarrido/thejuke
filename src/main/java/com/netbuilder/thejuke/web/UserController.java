package com.netbuilder.thejuke.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginContext;

import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.services.UserService;
import com.netbuilder.thejuke.util.Loggable;

@Named
@SessionScoped
@Loggable
public class UserController extends Controller implements Serializable {
	
	@Inject
	private UserService userService;
	
	@Inject
	private Credentials credentials;
	
	@Produces
	private User user;
	
	@Inject
	@SessionScoped
	private transient LoginContext loginContext;
	
	public void doLogin(){
		
	}
	
	public void doCreateAccount(){
		
	}

}
