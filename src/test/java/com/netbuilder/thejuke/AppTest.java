package com.netbuilder.thejuke;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.services.AlbumService;
import com.netbuilder.thejuke.services.ArtistService;
import com.netbuilder.thejuke.services.GenreService;
import com.netbuilder.thejuke.services.SongService;
import com.netbuilder.thejuke.web.SearchController;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	EntityManager em;
	EntityManagerFactory emf;
	GenreService genreService;
	SongService songService;
	AlbumService albumService;
	ArtistService artistService;
	SearchController searchController;
	/**Runs after every test. **/
	@Override
	protected void tearDown() throws Exception 
	{	
		List<Album> albumList= albumService.findAllAlbums();
		for(Album album : albumList)
		{
			albumService.removeAlbum(album);
		}
		List<Artist> artistList= artistService.findAllArtists();
		for(Artist artist : artistList)
		{
			artistService.removeArtist(artist);
		}
		List<Song> songList= songService.findAllSongs();
		for(Song song : songList)
		{
			songService.removeSong(song);
		}

		List<Genre> genreList= genreService.findAllGenres();
		for(Genre genre : genreList)
		{
			genreService.removeGenre(genre);
		}

		super.tearDown();
		em.getTransaction().commit();
    	
	}
	
	//Runs before every test
	 public void initialize() 
	 {
	       
	 }
    @Override
	protected void setUp() throws Exception 
    {
    	emf = Persistence.createEntityManagerFactory("TheJuke");

		em = emf.createEntityManager();
		genreService = new GenreService(em);
		songService = new SongService(em);
		albumService = new AlbumService(em);
		//artistService = new ArtistService(em);
		searchController= new SearchController(songService,albumService,genreService,artistService);
		em.getTransaction().begin();
		super.setUp();
	}
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public void testGenreHasDefaultName()
    {
    	Genre metal = new Genre();
    	assertFalse(metal.getName().equals("metal"));
    	assertTrue(metal.getName().equals("Not Found"));
    }
    public void testReadAllGenre()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	List<Genre> genreList2=genreService.findAllGenres();
    	assertTrue(genreList2.get(0).getName().equals("metal"));
    }
    public void testDeleteoneObject()
    {
    	Genre metal = new Genre("metal");
    	Genre rock = new Genre("rock");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreList.add(rock);
    	genreService.persistGenres(genreList);
    	genreService.removeGenre(metal);
    	assertTrue(genreService.findAllGenres().get(0).getName().equals("rock"));
    }
    public void testUpdate()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	metal.setName("Power Metal");
    	genreService.update(metal);
    	assertTrue(genreService.findAllGenres().get(0).getName().equals("Power Metal"));
    }
    public void testReadByID()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	
    	assertTrue(genreService.findGenre(metal.getId()).getName().equals("metal"));
    }
    public void testReturnNullIfImproperId()
    {
    	assertTrue(genreService.findGenre(-1)==null);
    }
    
    public void testReadByName()
    {
    	
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	assertTrue(genreService.findGenre("metal").get(0).getName().equals("metal"));
    }
    public void testSearchByName()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	List<Song> songList=new ArrayList<Song>();
    	songList.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songList.add(new Song("Let the Hammer fall", 3.00F, "C:\\music\\LetTheHammerFall.mp3", metal));
    	songService.persistSongs(songList);
    	
    	List<Song> songList2=searchController.searchByName("Sword");
    	assertTrue(songList2.get(0).getName().equals("Power of thy Sword"));
    }
    public void testSearchByAlbum()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	
    	List<Song> songList=new ArrayList<Song>();
    	songList.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songList.add(new Song("Let the Hammer fall", 3.00F, "C:\\music\\LetTheHammerFall.mp3", metal));
    	songService.persistSongs(songList);
    	
    	List<Album> albumList = new ArrayList<Album>();
    	albumList.add(new Album("Hammerfall", "producer",new Date(0), "C:\\music\\LetTheHammerFall.png", null, songList));
    	albumList.add(new Album("Man O War", "producer",new Date(0), "C:\\PowerOfThySword.png", null, songList));
    	albumService.persistAlbums(albumList);
    	
    	List<Song> songList2=searchController.searchByAlbum("Man O War");
    	assertTrue(songList2.get(0).getName().equals("Power of thy Sword"));
    }
    public void testSearchByGenre()
    {
    	Genre metal = new Genre("metal");
    	Genre rock = new Genre("rock");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreList.add(rock);
    	genreService.persistGenres(genreList);
    	
    	List<Song> songList=new ArrayList<Song>();
    	songList.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songList.add(new Song("Penny Lane", 3.00F, "C:\\music\\PennyLane.mp3", rock));
    	songService.persistSongs(songList);
    	
    	List<Song> songList2=searchController.searchByGenre("rock");
    	assertTrue(songList2.get(0).getName().equals("Penny Lane"));
    }
    public void testSearchByGenreWorksWithNull()
    {
    	Genre metal = new Genre("metal");
    	Genre rock = new Genre("rock");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreList.add(rock);
    	genreService.persistGenres(genreList);
    	
    	List<Song> songList=new ArrayList<Song>();
    	songList.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songList.add(new Song("Penny Lane", 3.00F, "C:\\music\\PennyLane.mp3", rock));
    	songService.persistSongs(songList);
    	
    	List<Song> songList2=searchController.searchByGenre("alt rock");
    	assertTrue(songList2.size()==0);
    }
    public void testSearchByGenreWorksWithNull2()
    {
    	Genre metal = new Genre("metal");
    	Genre rock = new Genre("rock");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreList.add(rock);
    	genreService.persistGenres(genreList);
    	
    	List<Song> songList=new ArrayList<Song>();
    	songList.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songList.add(new Song("Penny Lane", 3.00F, "C:\\music\\PennyLane.mp3", rock));
    	songService.persistSongs(songList);
    	
    	List<Song> songList2=searchController.searchByGenre(null);
    	assertTrue(songList2.size()==0);
    }
    public void testSearchByArtist()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	
    	List<Song> songList1=new ArrayList<Song>();
    	songList1.add(new Song("Power of thy Sword", 3.00F, "C:\\music\\PowerOfThySword.mp3", metal));
    	songService.persistSongs(songList1);
    	List<Song> songList2=new ArrayList<Song>();
    	songList2.add(new Song("Penny Lane", 3.00F, "C:\\music\\PennyLane.mp3", metal));
    	songService.persistSongs(songList2);
    	
    	List<Artist> artistList1 = new ArrayList<Artist>();
    	artistList1.add(new Artist("Manowar", "An Italian Heavy Metal band."));
    	
    	List<Artist> artistList2 = new ArrayList<Artist>();
    	artistList2.add(new Artist("Beatles", "Its the Beatles."));
    	
    	
    	List<Album> albumList1 = new ArrayList<Album>();
    	albumList1.add(new Album("Man O War", "producer",new Date(0), "C:\\PowerOfThySword.png", artistList1 , songList1));
    	List<Album> albumList2 = new ArrayList<Album>();
    	albumList2.add(new Album("Beatles", "producer",new Date(0), "C:\\PennyLane.png", artistList2 , songList2));
    	artistList1.get(0).setAlbumList(albumList1);
    	artistList2.get(0).setAlbumList(albumList2);
    	albumService.persistAlbums(albumList1); 
    	albumService.persistAlbums(albumList2); 
    	artistService.persistArtists(artistList1);
    	artistService.persistArtists(artistList2);
    	
    	List<Song> songList3=searchController.searchByArtist("Manowar");
    	assertTrue(songList3.get(0).getName().equals("Power of thy Sword"));
    }
}
