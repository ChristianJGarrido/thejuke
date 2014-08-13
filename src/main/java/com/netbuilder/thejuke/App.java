package com.netbuilder.thejuke;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TheJuke");
		
		EntityManager em = emf.createEntityManager();
		
		ArtistService artistService = new ArtistService(em);
		List<Artist> list = populateArtistList();
		
		artistService.persistArtist(list);
		artistService.listArtists();
		
		if(em != null){
			System.out.println("Entity Manager created successfully");
		} else {
			System.out.println("Failed to create Entity Manager");
		}
		
		em.close();
	}
	
	private static List<Artist> populateArtistList(){
		List<Artist> result = new ArrayList<Artist>();
		
//		result.add(new Artist("Nirvana", "The original grunge"));
//		result.add(new Artist("The Glitch Mob", "Popular electric music"));
//		result.add(new Artist("Rattatat", "More Muzak"));
//		result.add(new Artist("MGMT", "I like these guys"));
//		result.add(new Artist("Kanye West", "Gay Fish"));
		
		return result;
	}
}
