package com.netbuilder.thejuke.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import com.netbuilder.thejuke.entities.Admin;
import com.netbuilder.thejuke.entities.PlayList;

public class PlayListService implements Serializable {
	
	@Inject
	private EntityManager entityManager;

	public PlayListService(){
		
	}
	
	// Constructor
	public PlayListService(EntityManager entity) {
		this.entityManager = entity;
	}

	/**
	 * Adds a list of if PlayLists to the database
	 * 
	 * @param list
	 */
	public void persistPlayLists(List<PlayList> list) {
		for (PlayList PlayList : list) {
			entityManager.persist(PlayList);
		}
	}

	/**
	 * Prints out PlayLists for testing
	 */
	public void listPlayLists() {
		List<PlayList> list = entityManager.createQuery(
				"Select a from PlayList a", PlayList.class).getResultList();
		for (PlayList PlayList : list) {
			System.out.println(PlayList.toString());
		}
	}

	/**
	 * Queries database for all playlists and returns them in a List<PlayList>
	 * 
	 * @return List<PlayList>
	 */
	public List<PlayList> findAllPlayLists() {

		TypedQuery<PlayList> typedQuery = entityManager.createNamedQuery(
				PlayList.FIND_ALL, PlayList.class);
		return typedQuery.getResultList();

	}

	/**
	 * Searches through playlists by primary key and returns the playlist
	 * 
	 * @param key
	 * @return PlayList
	 */
	public PlayList findPlayList(final Long key) {

		if (key == null)
			throw new ValidationException("Invalid playlist id");

		return entityManager.find(PlayList.class, key);
	}

	/**
	 * Updates playlist in database and returns the playlist
	 * 
	 * @param playList
	 * @return PlayList
	 */
	public PlayList updatePlayList(PlayList playList) {
		if (playList == null) {
			throw new ValidationException("PlayList object is null");
		}

		return entityManager.merge(playList);
	}

	/**
	 * Creates PlayList in the database and creates Admin for it in database
	 * 
	 * @param song
	 * @return
	 */
	public PlayList persistPlayList(PlayList pl) {
		if (pl == null)
			throw new ValidationException("PlayList object is null");

		if (pl.getAdminId() != null && (Long) (pl.getAdminId().getId()) == null) {
			entityManager.persist(pl.getAdminId());
			//System.out.println("Here!");
		}

		entityManager.persist(pl);
		return pl;
	}

	/**
	 * Removes PlayList in Database by searching by PlayList object
	 * 
	 * @param song
	 */
	public void removePlayList(PlayList pl) {
		if (pl == null)
			throw new ValidationException("PlayList object is null");

		entityManager.remove(entityManager.merge(pl));
	}

	/**
	 * Removes playlist in Database by searching by PlayList ID
	 * 
	 * @param songId
	 */
	public void removePlayList(Long playlistID) {
		if (playlistID == null)
			throw new ValidationException("playlistID is null");

		removePlayList(findPlayList(playlistID));
	}

	/**
	 * Searches database for playlists with the specified Admin, returns a
	 * playlist list
	 * 
	 * @param a
	 * @return List<PlayList>
	 */
	public List<PlayList> findByAdmin(Admin a) {
		if (a == null)
			throw new ValidationException("Invalid genre");

		TypedQuery<PlayList> typedQuery = entityManager.createNamedQuery(
				PlayList.FIND_BY_ADMIN_ID, PlayList.class);
		typedQuery.setParameter("adminId", a.getId());

		return typedQuery.getResultList();
	}

	/**
	 * Searches database for PlayLists by name and returns a playlist list
	 * 
	 * @param name
	 * @return List<PlayList>
	 */
	public List<PlayList> findByPlayListName(String name) {
		if (name == null)
			throw new ValidationException("Invalid name");

		TypedQuery<PlayList> typedQuery = entityManager.createNamedQuery(
				PlayList.FIND_BY_PLAYLIST_NAME, PlayList.class);
		typedQuery.setParameter("playListName", name);

		return typedQuery.getResultList();
	}

	/**
	 * Searches through database based on a keyword and returns a playlist list
	 * that is similar to name or admin name
	 * 
	 * @param keyword
	 * @return List<PlayList>
	 */
	public List<PlayList> searchPlayLists(String keyword) {
		if (keyword == null)
			keyword = "";

		TypedQuery<PlayList> typedQuery = entityManager.createNamedQuery(
				PlayList.SEARCH, PlayList.class);
		typedQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");

		return typedQuery.getResultList();
	}

}
