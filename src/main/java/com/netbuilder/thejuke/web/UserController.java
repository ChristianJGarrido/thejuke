package com.netbuilder.thejuke.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
	private User loggedinUser;
	
//	@Inject
//	@SessionScoped
	private transient LoginContext loginContext;
	
	public String doCreateNewAccount(){
		
		if(credentials==null)
		{
			System.out.println("Credentials is null");
		}
//		if(credentials.getPassword().equals(credentials.getPassword2()))
//		{
//			System.out.println(credentials.getLogin());
//			System.out.println(credentials.getPassword());
//			//userService.persistUser(new User(username,password,0F));
//		}
//		else
//		{
//			System.out.println("Passwords don't match.");
//		}
		
		return "index";
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
