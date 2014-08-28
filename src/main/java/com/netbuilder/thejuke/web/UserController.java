package com.netbuilder.thejuke.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

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
	@LoggedIn
	private User loggedinUser;
	
	@Inject
	@SessionScoped
	private transient LoginContext loginContext;
	
	private String errorMessage="";
	
	public String doLogin() throws LoginException {
        if ("".equals(credentials.getLogin())) {
            //addWarningMessage("id_filled");
        	errorMessage="username not filled";
            return "flogin.faces";
        }
        if ("".equals(credentials.getPassword())) {
            //addWarningMessage("pwd_filled");
        	errorMessage="password not filled";
        	return "flogin.faces";
        }

        User check = userService.findUser(credentials.getLogin());
        if(check == null) {
        	errorMessage="invalid username";
        	return "flogin.faces";
        }
        else if(!credentials.getPassword().equals(check.getPassWord())) {
        	errorMessage="invalid password";
        	return "flogin.faces";
        }
        loginContext.login();
        loggedinUser = userService.findUser(credentials.getLogin());
        
        //return "#";
        errorMessage="";
        return "home.faces";
    }
	
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
	
	public boolean isLoggedIn() {
        return loggedinUser != null;
    }
	
	  public String doLogout() {
	        loggedinUser = null;
	        // Stop conversation
	      
	        //addInformationMessage("been_loggedout");
	        return "home.faces";
	    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
