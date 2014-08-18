package com.netbuilder.thejuke.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.exceptions.ValidationException;
import com.netbuilder.thejuke.util.Loggable;

/**
 * Provides methods to create, read, update, and destroy album objects from the
 * database.
 * 
 * @author Taylor Hunter
 *
 */

@Stateless
@Loggable
public class AlbumService implements Serializable{
	
	@Inject
	private EntityManager entityManager;

	public AlbumService(){
		
	}
	
	/**
	 * Sets up the service with the EntityManager in use
	 * 
	 * @param entity
	 *            Entity Manager being used
	 */
	public AlbumService(EntityManager entity) {
		this.entityManager = entity;
	}

	/**
	 * Finds the album with the provided ID if it exists
	 * 
	 * @param id
	 *            Unique id of the album
	 * @return the matching album
	 */
	public Album findAlbum(final Long id) {
		if (id == null)
			throw new ValidationException("id is invalid");

		return entityManager.find(Album.class, id);
	}

	/**
	 * Adds a record for a new album to the database
	 * 
	 * @param album
	 *            object to be added
	 * @return the added object
	 */
	public Album persistAlbum(final Album album) {
		if (album == null)
			throw new ValidationException("Album Object is null");
		entityManager.persist(album);
		return album;
	}

	/**
	 * adds a record for each new object to the database
	 * 
	 * @param list
	 *            List of album objects to be added
	 */
	public void persistAlbums(List<Album> list) {
		for (Album album : list) {
			entityManager.persist(album);
		}
	}

	/**
	 * finds all albums with the provided name
	 * 
	 * @param name
	 *            name of the albums to be found
	 * @return resulting list of album objects
	 */
	public List<Album> findAlbum(final String name) {
		if (name == null)
			throw new ValidationException("name is invalid");

		TypedQuery<Album> typedQuery = entityManager.createNamedQuery(
				Album.FIND_BY_NAME, Album.class);
		typedQuery.setParameter("name", name);
		return typedQuery.getResultList();
	}

	/**
	 * Gets all of the albums in the database
	 * 
	 * @return resulting list of album objects
	 */
	public List<Album> findAllAlbums() {
		TypedQuery<Album> typedQuery = entityManager.createNamedQuery(
				Album.FIND_ALL, Album.class);
		return typedQuery.getResultList();
	}

	/**
	 * Updates the provided album objects database entry
	 * 
	 * @param album
	 *            object to be updated
	 * @return the updated album object
	 */
	public Album updateAlbum(final Album album) {
		if (album == null)
			throw new ValidationException("album object is null");

		return entityManager.merge(album);
	}

	/**
	 * Removes the provided album object from the database
	 * 
	 * @param album
	 *            object to be removed
	 */
	public void removeAlbum(final Album album) {
		if (album == null)
			throw new ValidationException("album object is null");

		entityManager.remove(album);
	}

	/**
	 * Prints to console all of the album objexts in the database
	 */
	public void listAlbums() {
		List<Album> list = entityManager.createQuery("Select a from Album a",
				Album.class).getResultList();
		for (Album album : list) {
			System.out.println(album.toString());
		}
	}
}
