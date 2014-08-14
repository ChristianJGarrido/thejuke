package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

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
	
	public List<Song> findAllSongs() {
		
        TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_ALL, Song.class);
        return typedQuery.getResultList();
		
	}
	
	public Song findSong(final Long key) {
		
		if (key == null)
            throw new ValidationException("Invalid Song id");

        return entityManager.find(Song.class, key);
	}
	
	public Song updateSong(Song song) {
		if(song == null) {
			throw new ValidationException("Song object is null");
		}
		
		return entityManager.merge(song);
	}
	
	 public Song createItem(Song song) {
	        if (song == null)
	            throw new ValidationException("Song object is null");

	        if (song.getGenre() != null && (Integer)(song.getGenre().getId()) == null) {
	            entityManager.persist(song.getGenre());
	        }

	        entityManager.persist(song);
	        return song;
	    }
	
	public Song read(String name) {
		
		Query query = entityManager.createQuery("Select a from Song a where a.name = :sname");
		query.setParameter("sname", name);
		return (Song)query.getSingleResult();
	}
	
	public void removeSong(Song song) {
	        if (song == null)
	            throw new ValidationException("Song object is null");

	        entityManager.remove(entityManager.merge(song));
	}

	public void removeItem(Long songId) {
		if (songId == null)
	            throw new ValidationException("itemId is null");

	        removeSong(findSong(songId));
	}
	
	 public List<Song> findByGenre(Genre g) {
		 if (g == null)
			 throw new ValidationException("Invalid genre");

	     TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_BY_GENRE_ID, Song.class);
	     	typedQuery.setParameter("genreId", g.getId());
	     	
	     return typedQuery.getResultList();
	 }
	 
	 public List<Song> findBySongName(String name) {
		 if (name == null)
			 throw new ValidationException("Invalid name");

	     TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_BY_SONG_NAME, Song.class);
	     	typedQuery.setParameter("songName", name);
	     	
	     return typedQuery.getResultList();
	 }
	 
	 public List<Song> searchItems(String keyword) {
		 if (keyword == null)
	            keyword = "";
		 
		 TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.SEARCH, Song.class);
		 typedQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
	        
		 return typedQuery.getResultList();
	 }
	 
	
}
