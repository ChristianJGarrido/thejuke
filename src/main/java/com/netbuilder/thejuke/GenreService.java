package com.netbuilder.thejuke;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.ValidationException;

import junit.framework.TestCase;

public class GenreService {
	private EntityManager entityManager;

	public GenreService(EntityManager entity) {
		this.entityManager = entity;
	}
	//Create
	public void persistGenres(List<Genre> list){
		entityManager.getTransaction().begin();
		for(Genre genre : list){
			entityManager.persist(genre);

		}
		
		entityManager.getTransaction().commit();
	}
	//Readall
	public List<Genre> readAll()
	{
		List<Genre> list =  entityManager.createQuery("Select g from Genre g", Genre.class).getResultList();
		return list;	
	}
	//ReadbyKey
	
	public void listGenres(){
		List<Genre> list = entityManager.createQuery("Select g from Genre g", Genre.class).getResultList();
		for(Genre genre : list){
			System.out.print(genre.toString());
		}
	}
	public void delete(Genre genre)
	{
	if (genre == null)
	{
        throw new ValidationException("genre object is null");
	}
	entityManager.getTransaction().begin();
	entityManager.remove(entityManager.merge(genre));
	entityManager.getTransaction().commit();
	}
	public void update(Genre genre)
	{
		// Make sure the object is valid
        if (genre == null)
            throw new ValidationException("Customer object is null");
        entityManager.getTransaction().begin();
        // Update the object in the database
        entityManager.merge(genre);
        entityManager.getTransaction().commit();
		
	}
	public Genre read(int id)
	{
		List<Genre> list =  entityManager.createQuery("Select g from Genre g WHERE g.id ="+id, Genre.class).getResultList();
		if(list.size()==0)
		{
			return null;
		}
		return list.get(0);
	}
	public List<Genre> readByName(String name)
	{
		List<Genre> list =  entityManager.createQuery("Select g from Genre g WHERE g.name ='"+name+"'", Genre.class).getResultList();
		if(name==null)
		{
			return null;
		}
		return list;
	}
}
