package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.util.Loggable;

@Named
@SessionScoped
@Loggable	
@CatchException
public class CatalogueController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private SearchController searchController;
	
	private List<Song> songResultList;
	private List<Artist> artistResultList;
	private List<Album> albumResultList;
	
	private String keyword;
	
	public String doSearch() {
		if(keyword.equals(""))
		{
			//keyword="?";
			return "#";
		}
		System.out.println("Running code with keyword "+keyword);
		doFindSongByName(keyword);
		doFindArtistByName(keyword);
		findAlbumByName(keyword);
		//System.out.println("LOVE!\tlove!");
		return "searchresult.faces?keyword=" + keyword + "&faces-redirect=true";
	}
	
	public String doFindSongByName(String songName)
	{
		
		songResultList=searchController.searchByName(songName);
		return "searchresult.faces";
	}
	public String doFindSongByAlbum(String albumName)
	{
		
		songResultList=searchController.searchByAlbum(albumName);
		return "searchresult.faces";
	}
	public String doFindArtistByName(String artistName)
	{
		artistResultList=searchController.searchArtistByName(artistName);
		return "searchresult.faces";
	}
	public String findAlbumByName(String albumName)
	{
		albumResultList=searchController.searchAlbumByName(albumName);
		return "searchresult.faces";
	}
	public String findAlbumByArtist(String artistName)
	{
		albumResultList=searchController.searchAlbumByArtist(artistName);
		return "searchresult.faces";
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<Song> getSongResultList() {
		return songResultList;
	}
	public void setSongResultList(List<Song> songResultList) {
		this.songResultList = songResultList;
	}
	public List<Artist> getArtistResultList() {
		return artistResultList;
	}
	public void setArtistResultList(List<Artist> artistResultList) {
		this.artistResultList = artistResultList;
	}
	public List<Album> getAlbumResultList() {
		return albumResultList;
	}
	public void setAlbumResultList(List<Album> albumResultList) {
		this.albumResultList = albumResultList;
	}
	public SearchController getSearchController() {
		return searchController;
	}
	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}
}
