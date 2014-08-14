package com.netbuilder.thejuke;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.netbuilder.thejuke.entities.Admin;
import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.entities.PlayList;
import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.services.AdminService;
import com.netbuilder.thejuke.services.AlbumService;
import com.netbuilder.thejuke.services.ArtistService;
import com.netbuilder.thejuke.services.GenreService;
import com.netbuilder.thejuke.services.PlayListService;
import com.netbuilder.thejuke.services.SongService;
import com.netbuilder.thejuke.services.UserService;

/**
 * Hello world!
 *
 */
public class App implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -363084039824268881L;
	
	static List<Genre> genreList;
	static List<Song> songList;
	static List<Artist> artistList;
	static List<Album> albumList;

	static List<PlayList> playlistList;
	static List<User> userList;
	static List<Admin> adminList;
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TheJuke");

		EntityManager em = emf.createEntityManager();
		
		genreList = populateGenreList();
		songList = populateSongList();
		artistList = populateArtistList();
		albumList = populateAlbumList();
		userList = populateUserList();
		adminList = populateAdminList();
		playlistList = populatePlayListList();		

		ArtistService artistService = new ArtistService(em);
		artistService.persistArtists(artistList);
		artistService.listArtists();

		GenreService genreService = new GenreService(em);

//		genreService.persistGenres(genreList);
//		genreService.listGenres();
		System.out.println(genreService.readAll());

		SongService songService = new SongService(em);
		songService.persistSongs(songList);
		songService.listSongs();
		

		AlbumService albumService = new AlbumService(em);
		albumService.persistAlbums(albumList);
		albumService.listAlbums();

		UserService userService = new UserService(em);
		userService.persistUser(userList);
		userService.listUsers();
		
		AdminService adminService = new AdminService(em);
		adminService.persistAdmin(adminList);
		adminService.listAdmins();
		
		PlayListService playlistService = new PlayListService(em);
		playlistService.persistPlayLists(playlistList);
		playlistService.listPlayLists();
		
//		PlayList test = playlistService.find(1);
//		System.out.println(test);
		
		if(em != null){
			System.out.println("Entity Manager created successfully");
		} else {
			System.out.println("Failed to create Entity Manager");
		}

		em.close();
		emf.close();
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
		List<Artist> artist = new ArrayList<Artist>();
		artist.add(artistList.get(0));
		result.add(new Album("Nevermind", "Yalort", Date.valueOf("1991-6-30"), "path",artist, getNirvanaSongs()));
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
	
	private static List<User> populateUserList(){
		List<User> result = new ArrayList<User>();
		
		result.add(new User("Alorty", "pass"));
		result.add(new User("Erlym", "pass2"));
		return result;
	}
	
	private static List<Admin> populateAdminList(){
		List<Admin> result = new ArrayList<Admin>();
		
		result.add(new Admin(userList.get(0)));
		return result;
	}
	
	private static List<PlayList> populatePlayListList()
	{
		List<PlayList> result = new ArrayList<PlayList>();
		List<Song>playSongs = new ArrayList<Song>();
		playSongs.add(songList.get(0));
		playSongs.add(songList.get(1));
		PlayList play = new PlayList("My Playlist", playSongs, adminList.get(0));
		result.add(play);
		return result;
	}
	
}
