package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name="Song")
public class Song 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	
	@Column(name = "length")
	private float length;
	
	@Column(name = "audioPath")
	private String audioPath;
	
//	@Column(name = "Genre_ID")
//    private long genreId;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="Genre_ID",referencedColumnName="id")
    private Genre genre;
	
//	@ManyToMany(mappedBy="songList",fetch=FetchType.EAGER)
//    private List<Album> albumList;   
	
	public Song(String name, float length, String audioPath,Genre genre/*,List<Album> albumList*/)
	{
		this.name = name;
		this.length=length;
		this.audioPath=audioPath;
		//this.genreId=genreId;
		this.genre=genre;
//		this.albumList=albumList;
	}
//	public List<Album> getAlbumList() {
//		return albumList;
//	}
//	public void setAlbumList(List<Album> albumList) {
//		this.albumList = albumList;
//	}
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
//	public long getGenreId() {
//		return genreId;
//	}
//	public void setGenreId(long genreId) {
//		this.genreId = genreId;
//	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Song()
	{
		this.name = "Unknown Name";
		this.length = 0F;
		this.audioPath="";
	}
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Song {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name);
		sb.append("length='").append(length).append("', ");
		sb.append("genre='").append(genre).append("'}");
		return sb.toString();
		
	}
	
}
