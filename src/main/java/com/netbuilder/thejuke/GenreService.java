package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class GenreService {
	private EntityManager entityManager;

	public GenreService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistGenres(List<Genre> list){
		entityManager.getTransaction().begin();
		for(Genre genre : list){
			entityManager.persist(genre);

		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listGenres(){
		List<Genre> list = entityManager.createQuery("Select g from Genre g", Genre.class).getResultList();
		for(Genre genre : list){
			System.out.print(genre.toString());
		}
	}
}
