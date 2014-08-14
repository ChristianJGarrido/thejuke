package com.netbuilder.thejuke.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Playlist")
// Named Queries for easy reference for PlayListService.java
@NamedQueries({
		@NamedQuery(name = PlayList.FIND_BY_PLAYLIST_ID, query = "SELECT i FROM PlayList i WHERE i.id = :playListId"),
		@NamedQuery(name = PlayList.FIND_BY_ADMIN_ID, query = "SELECT i FROM PlayList i WHERE i.adminId = :adminId"),
		@NamedQuery(name = PlayList.FIND_BY_PLAYLIST_NAME, query = "SELECT i from PlayList i where i.name = :playListName"),
		@NamedQuery(name = PlayList.SEARCH, query = "SELECT i FROM PlayList i WHERE UPPER(i.name) LIKE :keyword OR UPPER(i.adminId.user.userName) LIKE :keyword ORDER BY i.name, i.adminId.user.userName"),
		@NamedQuery(name = PlayList.FIND_ALL, query = "SELECT i FROM PlayList i") })
public class PlayList {

	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "Admin_ID", referencedColumnName = "id")
	private Admin adminId;

	@Column(name = "name")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Playlist_has_Song", joinColumns = @JoinColumn(name = "Playlist_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Song_id", referencedColumnName = "id"))
	private List<Song> songList;

	// Statics names for the named queries to reference
	public static final String FIND_BY_PLAYLIST_ID = "PlayList.findByPlayListId";
	public static final String FIND_BY_ADMIN_ID = "PlayList.findByAdminId";
	public static final String FIND_BY_PLAYLIST_NAME = "PlayList.findByPlayListName";
	public static final String SEARCH = "PlayList.search";
	public static final String FIND_ALL = "PlayList.findAll";

	// Constructors
	public PlayList() {
		this.name = "Unknown Name";
		this.adminId = new Admin();
		this.songList = null;
	}

	public PlayList(String name, List<Song> songList, Admin adminId) {

		this.adminId = adminId;
		this.name = name;
		this.songList = songList;

	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Admin getAdminId() {
		return adminId;
	}

	public void setAdminId(Admin adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(PlayList pl) {
		this.name = pl.getName();
		this.songList = pl.getSongList();
		this.adminId = pl.getAdminId();
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	// to be able to print a PlayList
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Playlist {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name).append("', SongList {");
		for (Song s : songList) {
			sb.append(s);
			if (songList.get(songList.size() - 1) != s)
				sb.append(", ");
		}
		sb.append("}}");
		return sb.toString();

	}

}
