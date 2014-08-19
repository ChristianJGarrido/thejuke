package com.netbuilder.thejuke;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

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
import com.netbuilder.thejuke.util.Loggable;

/**
 * Hello world!
 *
 */

@Singleton
@Startup
@Loggable
@DataSourceDefinition(
		className = "org.apache.derby.jdbc.EmbeddedDataSource",
        name = "jukedb",
        user = "root",
        password = "P4ssword",
        databaseName = "thejukedb",
        properties = {"connectionAttributes=;create=true"}
		)
  public class DBPopulator {
	  
	  private static User user1;
	  private static User user2;
	  private static Admin admin1;
	  private static PlayList pl1;
	  private static PlayList pl2;
	  
	  private static List<Artist> artistList = new ArrayList<Artist>();
	  private static List<Album> albumList = new ArrayList<Album>();
	  private static List<Song> songList = new ArrayList<Song>();
	  private static List<Genre> genreList = new ArrayList<Genre>();
	
	  @Inject
	  private static UserService userService;
		
	  @Inject
	  private static AdminService adminService;
		
	  @Inject
	  private static ArtistService artistService;
		
	  @Inject
	  private static AlbumService albumService;
		
	  @Inject
	  private static SongService songService;
		
	  @Inject
	  private static GenreService genreService;
		
	  @Inject
	  private static PlayListService playListService;
	  
	  private static Song song1;
	  
	  @PostConstruct
	  private static void populateDB() {
		  //initUsers();
		  //initAdmin();
		  //initArtists();
		  //initGenres();
		  //initSongs();
		  //initAlbums();
		  //initPlayLists();
		 
	  }
	  
	  private static void initUsers() {
		//em.getTransaction().begin();
		user1 = new User("Bob", "Builder", 100F);
		user2 = new User("Christian James", "I suck", 100F);
		userService.persistUser(user1);
		userService.persistUser(user2);
		System.out.println(user1);
		System.out.println(user2);
	}

	private static void initAdmin() {
		admin1 = new Admin(user2);
		adminService.persistAdmin(admin1);
		System.out.println(admin1);

	}

	private static void initArtists() {

		artistList.add(new Artist("Nirvana", "The original grunge"));
		artistList.add(new Artist("The Glitch Mob", "Popular electric music"));
		artistList.add(new Artist("Rattatat", "More Muzak"));
		artistList.add(new Artist("MGMT", "I like these guys"));
		artistList.add(new Artist("Kanye West", "Gay Fish"));
		artistService.persistArtists(artistList);
		System.out.println(artistList);

	}

	private static void initGenres() {

		genreList.add(new Genre("Electronica"));
		genreList.add(new Genre("Rock Alternative"));
		genreList.add(new Genre("Metal"));
		genreService.persistGenres(genreList);
		System.out.println(genreList);

	}

	private static void initSongs() {
		song1 = new Song("Hail To The Hammer", 3.00F,
				"C:\\Music\\Tyr\\HailToTheHammer.mp3", genreList.get(2), 25F);
		songService.persistSong(song1);
		System.out.println(song1);

	}

	private static void initAlbums() {

		// albumList.add(artistList.get(0));
		List<Artist> arr = new ArrayList<Artist>();
		arr.add(artistList.get(0));
		albumList.add(new Album("Nevermind", "Yalort", Date
				.valueOf("1991-6-30"), "path", arr, getNirvanaSongs()));

		albumService.persistAlbums(albumList);
		System.out.println(albumList.get(0));

	}

	private static void initPlayLists() {

		List<Song> playSongs = new ArrayList<Song>();
		playSongs.add(songList.get(0));
		playSongs.add(songList.get(1));
		pl1 = new PlayList("My PlayList", playSongs, admin1);
		playListService.persistPlayList(pl1);
		System.out.println(pl1);
	}

	@PreDestroy
	private static void clearDB() {
		adminService.removeAdmin(admin1);
		// playListService.removePlayList(pl1);

		// for(Artist a: artistList)
		// artistService.removeArtist(a);
		// for(Album a: albumList)
		// albumService.removeAlbum(a);
		// songService.removeSong(song1);

		// for(Genre g: genreList) {
		// genreService.removeGenre(g);
		// }

		userService.removeUser(user1);
		// userService.removeUser(user2);

	}

	private static List<Song> getNirvanaSongs() {
		List<Song> result = new ArrayList<Song>();

		result.add(new Song("Smells like Teen Spirit", 5.01f,
				"C:\\Music\\Nirvana\\Nevermind\\SmellsTeenSpirit.mp3",
				genreList.get(1), .25F));
		result.add(new Song("Come as you are", 3.38f,
				"C:\\Music\\Nirvana\\Nevermind\\Comeasyouare.mp3", genreList
						.get(1), 25F));
		result.add(new Song("Lithium", 4.16f,
				"C:\\Music\\Nirvana\\Nevermind\\Lithium.mp3", genreList.get(1),
				.25F));
		songList.addAll(result);
		return result;
	}

	//
	// public static void main(String[] args) {
	//
	// populateDB();
	// System.out.println(user2.isAdmin());
	// clearDB();
	// System.out.println(user2.isAdmin());
	//
	// em.close();
	// emf.close();
	//
	//
	//
	// }

}
