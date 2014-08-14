package com.netbuilder.thejuke.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
		@NamedQuery(name = Album.FIND_ALL, query = "SELECT a FROM Album a"),
		@NamedQuery(name = Album.FIND_BY_NAME, query = "SELECT a FROM Album a WHERE a.name = :name") })
@Table(name = "Album")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;

	@Column(name = "producer")
	private String producer;

	@Column(name = "dateCreated")
	private Date dateCreated;

	@Column(name = "artPath")
	private String artPath;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Artist_has_Album", joinColumns = @JoinColumn(name = "Album_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Artist_id", referencedColumnName = "id"))
	private List<Artist> artistList;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Album_has_Song", joinColumns = @JoinColumn(name = "Album_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Song_id", referencedColumnName = "id"))
	private List<Song> songList;

	public static final String FIND_ALL = "Customer.findAll";
	public static final String FIND_BY_NAME = "Customer.findByName";

	public Album(String name, String producer, Date dateCreated,
			String artPath, List<Artist> artistList, List<Song> songList) {
		this.name = name;
		this.producer = producer;
		this.dateCreated = dateCreated;
		this.artPath = artPath;
		this.songList = songList;
		this.artistList = artistList;

	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getArtPath() {
		return artPath;
	}

	public void setArtPath(String artPath) {
		this.artPath = artPath;
	}

	public Album() {
		this.name = "Unknown Name";
		this.producer = "Unknown Producer";
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Album {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name).append("', ");
		sb.append("producer='").append(producer).append("', ");
		sb.append("date released='").append(dateCreated).append("', ");
		sb.append("songs='");
		for (Song s : songList) {
			sb.append(s.getName());
			if (songList.get(songList.size() - 1) != s)
				sb.append(", ");
		}
		sb.append("}}");
		return sb.toString();

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getProducer() {
		return producer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Artist> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<Artist> artistList) {
		this.artistList = artistList;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

}
