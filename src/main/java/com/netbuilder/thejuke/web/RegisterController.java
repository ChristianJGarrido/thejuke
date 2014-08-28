package com.netbuilder.thejuke.web;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.login.LoginException;
import javax.transaction.UserTransaction;

import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.services.UserService;

@Named
@RequestScoped
public class RegisterController 
{
//	@PersistenceContext(unitName = "TheJuke")
//	private EntityManager entityManager;
	
	@Inject
	private UserController userController;
	
	private String errorMessage="";
	
//	@Resource private UserTransaction utx; 
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public UserController getUserController() {
		return userController;
	}
	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	String login;
	
	//private UserService userService=new UserService();
	@Inject 
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	String password;
	String password2;
    public String doCreateNewAccount()
    {
    	if(login.length()==0 || password.length()==0 || password2.length()==0)
    	{
    		errorMessage="Please fill in the entire form";
			return"#";
    	}
    	if(login.length()<5)
    	{
    		errorMessage="Username has to be 5 characters or longer";
			return"#";
    	}
    	if(password.length()<5)
    	{
    		errorMessage="Password must have at least 5 characters";
			return"#";
    	}
    	//Usernames are all caps.
    	String userName=login.toLowerCase();
    	if(userService.findUser(userName)==null)
    	{
	    	//Password must be confirmed
			if(password.equals(password2))
			{
				try {
				System.out.println(login);
				System.out.println(password+"2");
				User registeredUser=new User(userName,password,0F);
				userService.persistUser(registeredUser);
				Credentials credentials = new Credentials();
				credentials.setLogin(userName);
				credentials.setPassword(password);
				credentials.setPassword2(password2);
				//userController.setCredentials(credentials);
				//userController.doLogin();
				userController.setLoggedinUser(registeredUser);
				} catch (SecurityException | IllegalStateException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				errorMessage="Passwords don't match.";
				return"#";
			}	
    	}
    	else
    	{
    		errorMessage="User already exists.";
    		return"#";
    	}
		
		return "home.faces";
    }
}
