package com.netbuilder.thejuke;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.agoncal.application.petstore.domain.Item;

@Entity
@Table(name="Song")
@NamedQueries({
    @NamedQuery(name = Song.FIND_BY_SONG_ID, query = "SELECT i FROM Song i WHERE i.id = :songId"),
    @NamedQuery(name = Song.FIND_BY_GENRE_ID, query = "SELECT i FROM Song i WHERE i.genre.id = :genreId"),
    @NamedQuery(name = Song.FIND_BY_SONG_NAME, query = "SELECT i from Song i where i.name = :songName"),
    @NamedQuery(name = Song.SEARCH, query = "SELECT i FROM Song i WHERE UPPER(i.name) LIKE :keyword OR UPPER(i.genre.name) LIKE :keyword ORDER BY i.name, i.length, i.genre"),
    @NamedQuery(name = Song.FIND_ALL, query = "SELECT i FROM Song i")
})
public class Song 
{
	//Declarations and Entity annotations
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;

	@Column(name = "length")
	private float length;

	@Column(name = "audioPath")
	private String audioPath;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="Genre_ID",referencedColumnName="id")
    private Genre genre;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="Album_has_Song",
    		joinColumns=
            @JoinColumn(name="Song_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Album_id", referencedColumnName="id")
    )
    private List<Album> albumList;   
	
	public static final String FIND_BY_SONG_ID = "Song.findBySongId";
	public static final String FIND_BY_GENRE_ID = "Song.findByGenreId";
	public static final String FIND_BY_SONG_NAME = "Song.findBySongName";
    public static final String SEARCH = "Song.search";
    public static final String FIND_ALL = "Song.findAll";

	//Constructor
	public Song(String name, float length, String audioPath,Genre genre)
	{
		this.name = name;
		this.length=length;
		this.audioPath=audioPath;
		this.genre=genre;
	}
	//Getters and Setters
	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Song() {
		this.name = "Unknown Name";
		this.length = 0F;

		this.audioPath="";
		this.genre=new Genre();
	}
	//toString for easy testing.
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Song {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name).append(", ");
		sb.append("length='").append(length).append("', ");
		sb.append("genre='").append(genre).append("'}");
		return sb.toString();

	}
	
	

}
