package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class SongService {
	private EntityManager entityManager;

	public SongService(EntityManager entity) 
	{
		this.entityManager = entity;
	}
	
	public void persistArtist(List<Song> list)
	{
		entityManager.getTransaction().begin();
		for(Song song : list)
		{
			entityManager.persist(song);

		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listArtists()
	{
		List<Song> list = entityManager.createQuery("Select s from Song s", Song.class).getResultList();
		for(Song song : list)
		{
			System.out.println(song.toString());
		}
	}
}
