package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.entities.Song;


public class SongService {
	
	private EntityManager entityManager;
	
	
	/**
	 * Constructor
	 * @param entity
	 */
	public SongService(EntityManager entity) 
	{
		this.entityManager = entity;
	}
	
	/**Takes in a list of songs. Commits them to database in its entityManager. **/
	public void persistSongs(List<Song> list)
	{
		for(Song song : list)
		{
			entityManager.persist(song);

		}
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
	
	/**
	 * Queries database for all songs and returns them in a List<Song>
	 * @return List<Song>
	 */
	public List<Song> findAllSongs() {
		
        TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_ALL, Song.class);
        return typedQuery.getResultList();
		
	}
	
	/**
	 * Searches through songs by primary key and returns the song
	 * @param key
	 * @return Song
	 */
	public Song findSong(final Long key) {
		
		if (key == null)
            throw new ValidationException("Invalid Song id");

        return entityManager.find(Song.class, key);
	}
	
	/**
	 * Updates song in database and returns the Song
	 * @param song
	 * @return Song
	 */
	public Song updateSong(Song song) {
		if(song == null) {
			throw new ValidationException("Song object is null");
		}
		
		return entityManager.merge(song);
	}
	
	/**
	 * Creates song in the database and creates Genre for it in database
	 * @param song
	 * @return
	 */
	 public Song persistSong(Song song) {
	        if (song == null)
	            throw new ValidationException("Song object is null");

	        if (song.getGenre() != null && (Integer)(song.getGenre().getId()) == null) {
	            entityManager.persist(song.getGenre());
	        }

	        entityManager.persist(song);
	        return song;
	    }
	
	/**
	 * Removes song in Database by searching by Song object
	 * @param song
	 */
	public void removeSong(Song song) {
	        if (song == null)
	            throw new ValidationException("Song object is null");

	        entityManager.remove(entityManager.merge(song));
	}

	/**
	 * Removes song in Database by searching by Song ID
	 * @param songId
	 */
	public void removeSong(Long songId) {
		if (songId == null)
	            throw new ValidationException("SongId is null");

	        removeSong(findSong(songId));
	}
	
	/**
	 * Searches database for songs with the specified Genre and returns a song list
	 * @param g
	 * @return List<Song>
	 */
	public List<Song> findByGenre(Genre g) {
		 if (g == null)
			 throw new ValidationException("Invalid genre");

	     TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_BY_GENRE_ID, Song.class);
	     	typedQuery.setParameter("genreId", g.getId());
	     	
	     return typedQuery.getResultList();
	}
	
	/**
	 * Searches database for songs by name and returns a song list
	 * @param name
	 * @return List<Song>
	 */
	public List<Song> findBySongName(String name) {
		 if (name == null)
			 throw new ValidationException("Invalid name");

	     TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.FIND_BY_SONG_NAME, Song.class);
	     	typedQuery.setParameter("songName", name);
	     	
	     return typedQuery.getResultList();
	}
	
	/**
	 * Searches through database based on a keyword and returns a song list that is similar to name or genre
	 * @param keyword
	 * @return
	 */
	public List<Song> searchSongs(String keyword) {
		 if (keyword == null)
	            keyword = "";
		 
		 TypedQuery<Song> typedQuery = entityManager.createNamedQuery(Song.SEARCH, Song.class);
		 typedQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
	        
		 return typedQuery.getResultList();
	}
	 
	
}
