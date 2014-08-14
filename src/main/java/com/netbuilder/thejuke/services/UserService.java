package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.netbuilder.thejuke.entities.User;

public class UserService {
	private EntityManager entityManager;

	public UserService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistUser(List<User> list)
	{
		for(User user : list){
			entityManager.persist(user);
		}
	}
	public void persistUser(User user)
	{
			entityManager.persist(user);
	}
	
	
	public void listUsers(){
		List<User> list = entityManager.createQuery("Select a from User a", User.class).getResultList();
		for(User User : list){
			System.out.println(User.toString());
		}
	}
	
	public List<User> readAll() {
		
		List<User> list = entityManager.createQuery("Select a from User a", User.class).getResultList();
		return list;
		
	}
	
	public User read(long key) {
		
		return entityManager.find(User.class, key);
	}
	
	public void update(long key, User user) {
		
		User get = entityManager.find(User.class, key);
		get.update(user);
	}

}

