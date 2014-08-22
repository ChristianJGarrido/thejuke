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
public class CatalogueController implements Serializable

{
	private List<Song> songResultList;
	private List<Artist> artistResultList;
	private List<Album> albumResultList;

	@Inject
	private SearchController searchController;
	
	private String keyword;
	
	public String doSearch() {
		doFindSongByName(keyword);
		doFindArtistByName(keyword);
		findAlbumByName(keyword);
		return "searchresult.faces";
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
}
