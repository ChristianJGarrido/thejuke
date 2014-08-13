package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class ArtistService {
	private EntityManager entityManager;

	public ArtistService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistArtist(List<Artist> list){
		entityManager.getTransaction().begin();
		for(Artist artist : list){
			entityManager.persist(artist);
		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listArtists(){
		List<Artist> list = entityManager.createQuery("Select a from Artist a", Artist.class).getResultList();
		for(Artist artist : list){
			System.out.println(artist.toString());
		}
	}
	

}
