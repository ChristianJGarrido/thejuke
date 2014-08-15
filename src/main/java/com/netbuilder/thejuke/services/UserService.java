package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.User;

public class UserService {
	private EntityManager entityManager;

	public UserService(EntityManager entity) {
		this.entityManager = entity;
	}
	/**
	 * Create multiple users at once
	 * @param pass in list of users
	 */
	public void persistUser(List<User> list){
		for(User User : list){
			entityManager.persist(User);

		}
	}
	/**
	 * Create one user
	 * @param user data
	 * @return user to be added
	 */
	public User persistUser(User user)
	{
		if(user==null)
			throw new ValidationException("Customer object is null");
		entityManager.persist(user);
		return user;
	}
	
	/**
	 * send all data in table
	 * @return  all user data that was searched
	 */
	public List<User> findAll() {
		TypedQuery<User> typedQuery = entityManager.createNamedQuery(User.FIND_ALL, User.class);
        return typedQuery.getResultList();
		
	}
	/**
	 * read a single row
	 * @param primary key/ID
	 * @return user data from table
	 */
	public User findUser(long key) {
		
		return entityManager.find(User.class, key);
	}
	
	/**
	 * find User by userName
	 * @param userName to be searched
	 * @return null if invalid or return User 
	 */
	public User findUser(String userName)
	{
		if (userName == null)
	        throw new ValidationException("Invalid User Name");
	
	    TypedQuery<User> typedQuery = entityManager.createNamedQuery(User.FIND_BY_USERNAME, User.class);
	    typedQuery.setParameter("userName", userName);
	
	    try {
	        return typedQuery.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	/**
	 * find User by userName and Password
	 * @param userName
	 * @param password
	 * @return user searched from table
	 */
	public User findUser(String userName, String password) {
	
	    if (userName == null)
	        throw new ValidationException("Invalid login");
	    if (password == null)
	        throw new ValidationException("Invalid password");
	
	    TypedQuery<User> typedQuery = entityManager.createNamedQuery(User.FIND_BY_PASSWORD, User.class);
	    typedQuery.setParameter("userName", userName);
	    typedQuery.setParameter("password", password);
	    return typedQuery.getSingleResult();
	}
	/**
	 * update user information in 1 row
	 * @param user object to be updated
	 */
	public User updateUser(final User user) {

        // Make sure the object is valid
        if (user == null)
            throw new ValidationException("User object is null");

        // Update the object in the database
        entityManager.merge(user);

        return user;
    } 	
	/**
	 * remove user from table.
	 * @param user object to be deleted
	 */
	public void removeUser(User user)
	{
		AdminService a = new AdminService(entityManager);
		if (user==null)
			throw new ValidationException("Invalid user ID");
		if(user.isAdmin()) {
			a.removeAdmin(a.findByUserId(user.getId()));
		}
		
		entityManager.remove(entityManager.merge(user));
	}
	/**
	 * print user list
	 */
	public void listUsers(){
		List<User> list = entityManager.createQuery("Select a from User a", User.class).getResultList();
		for(User User : list){
			System.out.println(User.toString());
		}
	}
	
        
}

