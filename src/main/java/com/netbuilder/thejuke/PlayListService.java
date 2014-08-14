package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.EntityManager;

public class PlayListService {
	private EntityManager entityManager;

	public PlayListService(EntityManager entity) {
		this.entityManager = entity;
	}
	
	public void persistPlayList(List<PlayList> list){
		entityManager.getTransaction().begin();
		for(PlayList PlayList : list){
			entityManager.persist(PlayList);

		}
		
		entityManager.getTransaction().commit();
	}
	
	
	public void listPlayLists(){
		List<PlayList> list = entityManager.createQuery("Select a from PlayList a", PlayList.class).getResultList();
		for(PlayList PlayList : list){
			System.out.println(PlayList.toString());
		}
	}
	
	public List<PlayList> readAll() {
		
		List<PlayList> list = entityManager.createQuery("Select a from PlayList a", PlayList.class).getResultList();
		return list;
		
	}
	
	public PlayList read(long key) {
		
		return entityManager.find(PlayList.class, key);
	}
	
	public void update(long key, PlayList pl) {
		
		PlayList play = entityManager.find(PlayList.class, key);
		
		entityManager.getTransaction().begin();
		play.update(pl);
		entityManager.getTransaction().commit();
	}

}
