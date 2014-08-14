package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;

import com.netbuilder.thejuke.entities.Admin;

public class AdminService {
	private EntityManager entityManager;

	public AdminService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistAdmin(List<Admin> list){
		entityManager.getTransaction().begin();
		for(Admin admin : list){
			entityManager.persist(admin);
		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listAdmins(){
		List<Admin> list = entityManager.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
		for(Admin admin : list){
			System.out.println(admin.toString());
		}
	}
	
	public List<Admin> readAll() {
		
		List<Admin> list = entityManager.createQuery("Select a from Admin a", Admin.class).getResultList();
		return list;
		
	}
	
	public Admin read(long key) {
		
		return entityManager.find(Admin.class, key);
	}
	
	public void update(long key, Admin admin) {
		
		Admin get = entityManager.find(Admin.class, key);
		
		entityManager.getTransaction().begin();
		get.update(admin);
		entityManager.getTransaction().commit();
	}

}
