package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Genre;
import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.services.AlbumService;
import com.netbuilder.thejuke.services.ArtistService;
import com.netbuilder.thejuke.services.GenreService;
import com.netbuilder.thejuke.services.SongService;
import com.netbuilder.thejuke.util.Loggable;

@Named
@SessionScoped
@Loggable
public class SearchController implements Serializable {
	
	@Inject
	private SongService songService;
	@Inject
	private AlbumService albumService;
	@Inject
	private GenreService genreService;
	@Inject
	private ArtistService artistService;
	
	private String keyword;


	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Song> searchByName(String name) {
		return songService.searchSongs(name);
	}

	public List<Song> searchByArtist(String artistName) {
		List<Song> songList = new ArrayList<Song>();
		List<Artist> artistList = artistService.findArtist(artistName);
		System.out.println(artistList.size());
		if (artistList != null) {
			for (Artist artist : artistList) {
				List<Album> albumList = artist.getAlbumList();
				for (Album album : albumList) {
					songList.addAll(this.searchByAlbum(album.getName()));
				}
			}

		}
		return songList;
	}

	public List<Song> searchByAlbum(String string) {
		List<Album> albumList = albumService.findAlbum(string);
		List<Song> songList = new ArrayList<Song>();
		if (albumList != null) {
			for (Album album : albumList) {
				songList.addAll(album.getSongList());
			}
		}
		return songList;
	}

	public List<Song> searchByGenre(String genreName) {
		List<Genre> genreList = genreService.findGenre(genreName);
		List<Song> songList = new ArrayList<Song>();
		if (genreList != null) {
			for (Genre genre : genreList) {
				songList.addAll(songService.findByGenre(genre));
			}
		}
		return songList;
	}
	public List<Artist> searchArtistByName(String artistName)
	{
		return(artistService.findArtist(artistName));
	}

	public List<Album> searchAlbumByName(String albumName) {
		return(albumService.findAlbum(albumName));
	}

	public List<Album> searchAlbumByArtist(String artistName) {
		List<Artist> artistList =artistService.findArtist(artistName);
		List<Album> albumList = new ArrayList<Album>();
		if (artistList != null) {
			for (Artist artist : artistList) {
				albumList.addAll(albumService.findByArtist(artist));
			}
		}
		return albumList;
	}

}
