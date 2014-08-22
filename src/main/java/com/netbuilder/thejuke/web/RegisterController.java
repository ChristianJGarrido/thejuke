package com.netbuilder.thejuke.web;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.services.UserService;

@Named
@RequestScoped
public class RegisterController 
{
	@PersistenceContext(unitName = "TheJuke")
	private EntityManager entityManager;
	
	@Resource 
	private UserTransaction utx; 
	
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
				userService.persistUser(new User(userName,password,0F));
				} catch (SecurityException | IllegalStateException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Passwords don't match.");
			}	
    	}
    	else
    	{
    		System.out.println("User already exists.");
    	}
		
		return "index";
    }
}
