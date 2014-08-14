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
	
	/**Queries the database for songs, then prints them.(For testing) **/
	public void listSongs()
	{
		List<Song> list = entityManager.createQuery("Select s from Song s", Song.class).getResultList();
		for(Song song : list)
		{
			System.out.println(song.toString());
		}
	}
	
	public List<Song> readAll() {
		
		List<Song> list = entityManager.createQuery("Select a Song a", Song.class).getResultList();
		return list;
		
	}
	
	public Song read(long key) {
		
		return entityManager.find(Song.class, key);
	}
	
	public void update(long key, Song song) {
		
		Song sg = entityManager.find(Song.class, key);
		
		entityManager.getTransaction().begin();
		sg.update(song);
		entityManager.getTransaction().commit();
		
	}
}
