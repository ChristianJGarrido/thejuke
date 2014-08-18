package com.netbuilder.thejuke.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.exceptions.ValidationException;
import com.netbuilder.thejuke.util.Loggable;

/**
 * Service for storing, retrieving, and updating
 * 
 * @author Taylor Hunter
 *
 */

@Stateless
@Loggable
public class ArtistService implements Serializable {
	
	@Inject
	private EntityManager em;

	public ArtistService(){
		
	}
	
	/**
	 * Constructor that supplies a reference to the Entity Manager.
	 * 
	 * @param entity
	 *            Entity Manager being used
	 */
	public ArtistService(EntityManager em) {
		this.em = em;
	}

	/**
	 * Adds the supplied artists to the database
	 * 
	 * @param list
	 *            List of artists to be added
	 */
	public void persistArtists(List<Artist> list) {
		for (Artist artist : list) {
			em.persist(artist);
		}
	}

	/**
	 * Create an entry in the Database for a new Artist
	 * 
	 * @param artist
	 *            Artist to be added
	 * @return The artist that was added
	 */
	public Artist persistArtist(final Artist artist) {
		if (artist == null)
			throw new ValidationException("Artist Objext is null");

		em.persist(artist);

		return artist;
	}

	/**
	 * Finds the Artist with matching ID in the Database.
	 * 
	 * @param id
	 *            Desired Artist.id to find
	 * @return Returns the artist with the matching ID. May be null
	 */
	public Artist findArtist(Long id) {
		if (id == null)
			throw new ValidationException("Invalid ID");

		return em.find(Artist.class, id);
	}

	/**
	 * Finds all artists with a matching name.
	 * 
	 * @param name
	 *            Desired Artist.name to find
	 * @return List of all artists with matching name
	 */
	public List<Artist> findArtist(final String name) {
		if (name == null)
			throw new ValidationException("Invalid name");

		TypedQuery<Artist> tq = em.createNamedQuery(Artist.FIND_BY_NAME,
				Artist.class);
		tq.setParameter("name", name);
		return tq.getResultList();
	}

	/**
	 * Finds all artists stored in the database.
	 * 
	 * @return List of all artists
	 */
	public List<Artist> findAllArtists() {
		TypedQuery<Artist> typedQuery = em.createNamedQuery(Artist.FIND_ALL,
				Artist.class);
		return typedQuery.getResultList();
	}

	// TODO: public List<Artist> findArtists(Album album){}

	/**
	 * Updates the supplied artist within the database if it exists, otherwise
	 * creates a new record.
	 * 
	 * @param artist
	 *            The modified artist to be updated within the database
	 * @return The updated artist
	 */
	public Artist updateArtist(final Artist artist) {
		if (artist == null)
			throw new ValidationException("Artist object is null");

		return em.merge(artist);
	}

	/**
	 * 
	 * @param artist
	 *            Removes an artist by object if it exists, otherwise does
	 *            nothing.
	 */
	public void removeArtist(final Artist artist) {
		if (artist == null)
			throw new ValidationException("Artist object is null");

		em.remove(em.merge(artist));
	}

	/**
	 * Prints all existing entities within the database
	 */
	public void listArtists() {
		List<Artist> list = em.createQuery("Select a from Artist a",
				Artist.class).getResultList();
		for (Artist artist : list) {
			System.out.println(artist.toString());
		}
	}
}
