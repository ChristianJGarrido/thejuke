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
public class UserController implements Serializable {
	
	@Inject
	private UserService userService;
	
	@Inject
	private Credentials credentials;
	
	@Produces
	private User loggedinUser;
	
//	@Inject
//	@SessionScoped
	private transient LoginContext loginContext;
	
	public UserController(){
		
	}
	
	public String doCreateNewAccount(){
		return "";
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public User getLoggedinUser() {
		return loggedinUser;
	}

	public LoginContext getLoginContext() {
		return loginContext;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void setLoggedinUser(User loggedinUser) {
		this.loggedinUser = loggedinUser;
	}

	public void setLoginContext(LoginContext loginContext) {
		this.loginContext = loginContext;
	}
	
	
}
