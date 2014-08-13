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
public class App 
{
	static List<Genre> genreList;
	static List<Song> songList;
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TheJuke");
		
		EntityManager em = emf.createEntityManager();
		
		ArtistService artistService = new ArtistService(em);
		List<Artist> list = populateArtistList();
		
		artistService.persistArtist(list);
		artistService.listArtists();
		
		GenreService genreService = new GenreService(em);
		genreList = populateGenreList();
		
		genreService.persistGenres(genreList);
		genreService.listGenres();
		
		SongService songService = new SongService(em);
		songList = populateSongList();
		
		songService.persistSongs(songList);
		songService.listSongs();
		
		if(em != null){
			System.out.println("Entity Manager created successfully");
		} else {
			System.out.println("Failed to create Entity Manager");
		}
		
		em.close();
	}
	
	private static List<Artist> populateArtistList(){
		List<Artist> result = new ArrayList<Artist>();
		
		result.add(new Artist("Nirvana", "The original grunge"));
		result.add(new Artist("The Glitch Mob", "Popular electric music"));
		result.add(new Artist("Rattatat", "More Muzak"));
		result.add(new Artist("MGMT", "I like these guys"));
		result.add(new Artist("Kanye West", "Gay Fish"));
		return result;
	}
	private static List<Genre> populateGenreList()
	{
		List<Genre> result = new ArrayList<Genre>();
		result.add(new Genre("Metal"));
		return result;
	}
	private static List<Song> populateSongList()
	{
		List<Song> result = new ArrayList<Song>();
		result.add(new Song("Hail To The Hammer",3.00F,"C:\\Music\\Tyr\\HailToTheHammer.mp3", genreList.get(0)));
		return result;
	}
}
