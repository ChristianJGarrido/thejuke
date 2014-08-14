package com.netbuilder.thejuke.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.Genre;

public class GenreService  implements Serializable{
	private EntityManager entityManager;

	public GenreService()
	{
		
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public GenreService(EntityManager entity) {
		this.entityManager = entity;
	}

	/**
	 * Adds list of Genres to database
	 * 
	 * @param list
	 */
	public void persistGenres(List<Genre> list) {
		for (Genre genre : list) {
			entityManager.persist(genre);
		}
	}

	/**
	 * Adds Genre to database
	 * 
	 * @param genre
	 */
	public void persistGenre(Genre genre) {
		entityManager.persist(genre);
	}

	/** Returns all the genres in the database **/
	public List<Genre> readAll() {
		List<Genre> list = entityManager.createQuery("Select g from Genre g",
				Genre.class).getResultList();
		return list;
	}

	/** Prints out all genres in database. **/
	public void listGenres() {
		List<Genre> list = entityManager.createQuery("Select g from Genre g",
				Genre.class).getResultList();
		for (Genre genre : list) {
			System.out.print(genre.toString());
		}
	}

	/**
	 * Deletes selected genre from database
	 * 
	 * @param genre
	 */
	public void removeGenre(final Genre genre) {
		if (genre == null) {
			throw new ValidationException("genre object is null");
		}
		entityManager.remove(entityManager.merge(genre));
	}

	/**
	 * Commits changes to genre to database
	 * 
	 * @param genre
	 */
	public Genre update(Genre genre) {
		// Make sure the object is valid
		if (genre == null)
			throw new ValidationException("Customer object is null");
		entityManager.merge(genre);
		return genre;

	}

	/**
	 * Returns genre with given id
	 * 
	 * @param id
	 * @return Genre
	 */
	public Genre findGenre(int id) {
		List<Genre> list = entityManager.createQuery(
				"Select g from Genre g WHERE g.id =" + id, Genre.class)
				.getResultList();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * Returns all genres with given name.
	 * 
	 * @param name
	 * @return List<Genre>
	 */
	public List<Genre> findGenre(String name) {
		List<Genre> list = entityManager.createQuery(
				"Select g from Genre g WHERE g.name ='" + name + "'",
				Genre.class).getResultList();
		if (name == null) {
			return null;
		}
		return list;
	}
}
