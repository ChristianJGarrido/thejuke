package com.netbuilder.thejuke;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
//	@Override
//	protected void tearDown() throws Exception 
//	{
//		try
//		{
//		em.getTransaction().begin();
//		Query query1=em.createNativeQuery("SET SQL_SAFE_UPDATES = 0;");
//		Query query2=em.createNativeQuery("DELETE from genre");
//		Query query3=em.createNativeQuery("SET SQL_SAFE_UPDATES = 1;");
//		query1.executeUpdate();
//		query2.executeUpdate();
//		query3.executeUpdate();
//		em.close();
//		emf.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		super.tearDown();
//	}
	
	//Runs before every test
	 public void initialize() 
	 {
	       
	 }
    @Override
	protected void setUp() throws Exception 
    {
    	emf = Persistence
				.createEntityManagerFactory("TheJuke");

		em = emf.createEntityManager();
		genreService = new GenreService(em);
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
    	genreService.delete(metal);
    }
    public void testDeleteoneObject()
    {
    	Genre metal = new Genre("metal");
    	Genre rock = new Genre("rock");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreList.add(rock);
    	genreService.persistGenres(genreList);
    	genreService.delete(metal);
    	assertTrue(genreService.readAll().get(0).getName().equals("rock"));
    	genreService.delete(metal);
    	genreService.delete(rock);
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
    	genreService.delete(metal);
    }
    public void testReadByID()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	
    	assertTrue(genreService.read(metal.getId()).getName().equals("metal"));
    	genreService.delete(metal);
    }
    public void testReturnNullIfImproperId()
    {
    	assertTrue(genreService.read(-1)==null);
    }
    
    public void testReadByName()
    {
    	Genre metal = new Genre("metal");
    	List<Genre> genreList = new ArrayList<Genre>();
    	genreList.add(metal);
    	genreService.persistGenres(genreList);
    	assertTrue(genreService.readByName("metal").get(0).getName().equals("metal"));
    	genreService.delete(metal);
    }
}
