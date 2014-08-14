package com.netbuilder.thejuke;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.services.GenreService;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	EntityManager em;
	EntityManagerFactory emf;
	GenreService genreService;
	/**Runs after every test. **/
	@Override
	protected void tearDown() throws Exception 
	{
		List<Genre> genreList= genreService.readAll();
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
    	List<Genre> genreList2=genreService.readAll();
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
    	assertTrue(genreService.readAll().get(0).getName().equals("rock"));
    }
    public void testUpdate()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	metal.setName("Power Metal");
    	genreService.update(metal);
    	assertTrue(genreService.readAll().get(0).getName().equals("Power Metal"));
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
}
