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
	

}
