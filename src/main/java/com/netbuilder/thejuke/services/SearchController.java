package com.netbuilder.thejuke.services;

import java.util.ArrayList;
import java.util.List;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.entities.Song;

public class SearchController 
{
	private SongService songService;
	private AlbumService albumService;
	private GenreService genreService;
	public SearchController(SongService songService,AlbumService albumService,GenreService genreService)
	{
		this.songService=songService;
		this.albumService=albumService;
		this.genreService=genreService;
		
	}
	public List<Song> searchByName(String name)
	{
		return songService.searchSongs(name);
	}
	public List<Song> searchByArtist()
	{
		return null;
		
	}
	public List<Song> searchByAlbum(String string) 
	{
		List<Album> albumList = albumService.findAlbum(string);
		List<Song> songList= new ArrayList<Song>();
		if(albumList!=null)
		{
			for(Album album : albumList)
			{
				songList.addAll(album.getSongList());
			}
		}
		return songList;
	}
	public List<Song> searchByGenre(String genreName) 
	{
		List<Genre> genreList = genreService.findGenre(genreName);
		List<Song> songList= new ArrayList<Song>();
		if(genreList !=null)
		{
			for(Genre genre : genreList)
			{
				songList.addAll(songService.findByGenre(genre));
			}
		}
		return songList;
	}

}
