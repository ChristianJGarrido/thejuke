package com.netbuilder.thejuke.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.*;
import javax.security.auth.login.LoginContext;
import com.netbuilder.thejuke.entities.*;
import com.netbuilder.thejuke.util.Loggable;
import java.io.Serializable;

@Named
@SessionScoped
@Loggable
public class UserController implements Serializable {
	
	private Credentials credentials;
	
	private User loggedinUser;
	
	private transient LoginContext loginContext;
	
	public UserController(){
		
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
