package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Song;

public class CatalogueController implements Serializable

{
	private List<Song> songResultList;
	private List<Artist> artistResultList;
	private List<Album> albumResultList;
	@Inject
	private SearchController searchController;
	public CatalogueController()
	{
		
	}
	public String doFindSongByName(String songName)
	{
		
		songResultList=searchController.searchByName(songName);
		return"";
	}
	public String doFindSongByAlbum(String albumName)
	{
		
		songResultList=searchController.searchByAlbum(albumName);
		return"";
	}
	public String doFindArtistByName(String artistName)
	{
		artistResultList=searchController.searchArtistByName(artistName);
		return"";
	}
	public String findAlbumByName(String albumName)
	{
		albumResultList=searchController.searchAlbumByName(albumName);
		return "";
	}
	public String findAlbumByArtist(String artistName)
	{
		albumResultList=searchController.searchAlbumByArtist(artistName);
		return "";
	}
}
