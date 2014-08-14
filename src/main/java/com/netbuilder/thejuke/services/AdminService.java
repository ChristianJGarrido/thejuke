package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.Admin;

public class AdminService {
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

	/** Commits updated Admin to Database **/
	public Admin persistAdmin(Admin admin) {
		if (admin == null) {
			throw new ValidationException("Admin object is null");
		}
		entityManager.merge(admin);
		return admin;
	}

	/** Removes selected Admin from Database **/
	public void removeAdmin(final Admin admin) {
		if (admin == null) {
			throw new ValidationException("Admin object is null");
		}
		entityManager.remove(entityManager.merge(admin));
	}

}
