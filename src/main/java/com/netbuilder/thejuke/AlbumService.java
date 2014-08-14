package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class AlbumService {
	private EntityManager entityManager;

	public AlbumService(EntityManager entity) {
		this.entityManager = entity;
	}

	public void persistAlbum(List<Album> list) {
		entityManager.getTransaction().begin();
		for (Album album : list) {
			entityManager.persist(album);
		}

		entityManager.getTransaction().commit();
	}

	public void listAlbums() {
		List<Album> list = entityManager.createQuery("Select a from Album a",
				Album.class).getResultList();
		for (Album album : list) {
			System.out.println(album.toString());
		}
	}

	public void listCompilationAlbums() {

	}

	public void listSinglesAlbums() {

	}

}
