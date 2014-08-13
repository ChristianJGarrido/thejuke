package com.netbuilder.thejuke;

import java.sql.Date;
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
	static List<Genre> genreList;
	static List<Song> songList;
	static List<Artist> artistList;
	static List<Album> albumList;

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TheJuke");

		EntityManager em = emf.createEntityManager();
		
		genreList = populateGenreList();
		songList = populateSongList();
		artistList = populateArtistList();
		albumList = populateAlbumList();

		ArtistService artistService = new ArtistService(em);

		artistService.persistArtist(artistList);
		artistService.listArtists();

		GenreService genreService = new GenreService(em);

		genreService.persistGenres(genreList);
		genreService.listGenres();

		SongService songService = new SongService(em);

		songService.persistSongs(songList);
		songService.listSongs();
		
		AlbumService albumService = new AlbumService(em);
		
		albumService.persistAlbum(albumList);
		albumService.listAlbums();

		if (em != null) {
			System.out.println("Entity Manager created successfully");
		} else {
			System.out.println("Failed to create Entity Manager");
		}

		em.close();
	}

	private static List<Artist> populateArtistList() {
		List<Artist> result = new ArrayList<Artist>();

		result.add(new Artist("Nirvana", "The original grunge"));
		result.add(new Artist("The Glitch Mob", "Popular electric music"));
		result.add(new Artist("Rattatat", "More Muzak"));
		result.add(new Artist("MGMT", "I like these guys"));
		result.add(new Artist("Kanye West", "Gay Fish"));
		return result;
	}
	
	private static List<Album> populateAlbumList(){
		List<Album> result = new ArrayList<Album>();
		
		result.add(new Album("Nevermind", "Yalort", Date.valueOf("1991-6-30"), "path", getNirvanaSongs()));
		return result;
	}

	private static List<Genre> populateGenreList() {
		List<Genre> result = new ArrayList<Genre>();
		result.add(new Genre("Electronica"));
		result.add(new Genre("Rock Alternative"));
		result.add(new Genre("Metal"));
		return result;
	}
	
	private static List<Song> getNirvanaSongs(){
		List<Song> result = new ArrayList<Song>();
		
		result.add(new Song("Smells like Teen Spirit", 5.01f, 
				"C:\\Music\\Nirvana\\Nevermind\\SmellsTeenSpirit.mp3", genreList.get(1)));
		result.add(new Song("Come as you are", 3.38f, 
				"C:\\Music\\Nirvana\\Nevermind\\Comeasyouare.mp3", genreList.get(1)));
		result.add(new Song("Lithium", 4.16f, 
				"C:\\Music\\Nirvana\\Nevermind\\Lithium.mp3", genreList.get(1)));
		songList.addAll(result);
		return result;
	}

	private static List<Song> populateSongList() {
		List<Song> result = new ArrayList<Song>();
		result.add(new Song("Hail To The Hammer", 3.00F,
				"C:\\Music\\Tyr\\HailToTheHammer.mp3", genreList.get(2)));
		return result;
	}
}
