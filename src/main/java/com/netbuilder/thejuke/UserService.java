package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class UserService {
	private EntityManager entityManager;

	public UserService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistUser(List<User> list){
		entityManager.getTransaction().begin();
		for(User User : list){
			entityManager.persist(User);

		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listUsers(){
		List<User> list = entityManager.createQuery("Select a from User a", User.class).getResultList();
		for(User User : list){
			System.out.println(User.toString());
		}
	}
	

}

