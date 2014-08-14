package com.netbuilder.thejuke.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.netbuilder.thejuke.entities.Artist;

/**
 * @author Taylor Hunter
 * Service for persisting and retrieving Artists
 */
public class ArtistService {
	private EntityManager em;

	/**
	 * Constructor that supplies a reference to the Entity Manager
	 * @param entity Entity Manager being used
	 */
	public ArtistService(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * 
	 * @param id Desired Artist.id to find
	 * @return Returns the artist with the matching ID. May be null
	 */
	public Artist findArtist(Long id){
		return em.find(Artist.class, id);
	}

	/**
	 * 
	 * @param name Desired Artist.name to find 
	 * @return List of all artists with matching name
	 */
	public List<Artist> findArtist(final String name){
		TypedQuery<Artist> tq = em.createNamedQuery(Artist.FIND_BY_NAME, Artist.class);
		tq.setParameter("name", name);
		return tq.getResultList();
	}
	
	/**
	 * 
	 * @return List of all artists
	 */
	public List<Artist> findAllArtists(){
		TypedQuery<Artist> typedQuery = em.createNamedQuery(Artist.FIND_ALL, Artist.class);
		return typedQuery.getResultList();
	}
	
	// TODO: public List<Artist> findArtists(Album album){}
	
	/**
	 * Updates the supplied artist within the database if it exists, otherwise creates
	 * a new record.
	 * @param artist The modified artist to be updated within the database
	 * @return The updated artist
	 */
	public Artist updateArtist(final Artist artist){
		em.merge(artist);
		
		return artist;
	}
	
	/**
	 * 
	 * @param artist Removes an artist by object if it exists, otherwise does nothing.
	 */
	public void removeArtist(final Artist artist){
		em.remove(em.merge(artist));
	}
	
	/**
	 * Adds the supplied artists to the database
	 * @param list List of artists to be added
	 */
	public void persistArtist(List<Artist> list){
		em.getTransaction().begin();
		for(Artist artist : list){
			em.persist(artist);
		}
		
		em.getTransaction().commit();
	}
	
	/**
	 * Prints all existing entities within the database
	 */
	public void listArtists(){
		List<Artist> list = em.createQuery("Select a from Artist a", Artist.class).getResultList();
		for(Artist artist : list){
			System.out.println(artist.toString());
		}
	}
	

}
