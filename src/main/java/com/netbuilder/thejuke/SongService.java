package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class SongService {
	private EntityManager entityManager;

	public SongService(EntityManager entity) 
	{
		this.entityManager = entity;
	}
	
	/**Takes in a list of songs. Commits them to database in its entityManager. **/
	public void persistSongs(List<Song> list)
	{
		entityManager.getTransaction().begin();
		for(Song song : list)
		{
			entityManager.persist(song);

		}
		
		entityManager.getTransaction().commit();
	}
	
	/**Queries the database for songs, then prints them. **/
	public void listSongs()
	{
		List<Song> list = entityManager.createQuery("Select s from Song s", Song.class).getResultList();
		for(Song song : list)
		{
			System.out.println(song.toString());
		}
	}
}
