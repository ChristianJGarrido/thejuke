package com.netbuilder.thejuke.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.Admin;
import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.util.Loggable;

@Stateless
@Loggable
public class AdminService implements Serializable {
	
	@Inject
	private EntityManager entityManager;

	public AdminService(EntityManager entity) {
		this.entityManager = entity;
	}

	/** Adds Admins to Database **/
	public void persistAdmins(List<Admin> list) {
		if (list == null) {
			throw new ValidationException("Admin List is null");
		}
		for (Admin admin : list) {
			entityManager.persist(admin);
		}
	}
	
	public Admin persistAdmin(Admin admin) {
		if (admin == null) {
			throw new ValidationException("Admin object is null");
		}
		//System.out.println("Here!");
		if (admin.getUser() != null && (Long)admin.getUser().getId() == null)
            entityManager.persist(admin.getUser());
		entityManager.persist(admin);
		return admin;
	}
	/** Prints out all Admins **/
	public void listAdmins() {
		List<Admin> list = entityManager.createQuery("SELECT a FROM Admin a",
				Admin.class).getResultList();
		for (Admin admin : list) {
			System.out.println(admin.toString());
		}
	}

	/** Returns list of all admins **/
	public List<Admin> findAllAdmin() {

		List<Admin> list = entityManager.createQuery("Select a from Admin a",
				Admin.class).getResultList();
		return list;

	}

	/** Finds Admin by Key **/
	public Admin findAdmin(long key) {

		return entityManager.find(Admin.class, key);
	}
	
	public Admin findByUserId(long key) {
			
		 TypedQuery<Admin> typedQuery = entityManager.createNamedQuery(Admin.FIND_BY_USER_ID, Admin.class);
		 typedQuery.setParameter("userID", key);
		 return typedQuery.getSingleResult();

	}

	/** Commits updated Admin to Database **/
	public Admin updateAdmin(Admin admin) {
		if (admin == null) {
			throw new ValidationException("Admin object is null");
		}
		//System.out.println("Here!");
		entityManager.merge(admin);
		return admin;
	}

	/** Removes selected Admin from Database **/
	public void removeAdmin(Admin admin) {
		
		if (admin == null) {
			throw new ValidationException("Admin object is null");
		}
		admin.getUser().setAdmin(false);
		
		entityManager.remove(entityManager.merge(admin));
	}

}
