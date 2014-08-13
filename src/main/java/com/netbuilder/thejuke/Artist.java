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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Artist")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	
	
	// TODO: get this to fill in the artist_has_album table.
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="artist_has_album",
    		joinColumns=
            @JoinColumn(name="Artist_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="Album_id", referencedColumnName="id")
    )
	private List<Album> albumList;
	
	@Column(name = "bio")
	private String bio;
	
	public Artist(String name, String bio, List<Album> albumList){
		this.name = name;
		this.bio = bio;
		this.albumList = albumList;
	}
	
	public Artist(){
		this.name = "Unknown Name";
		this.bio = "Empty Bio";
	}
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Artist {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name).append("', ");
		sb.append("bio='").append(bio).append("'}");
		return sb.toString();
		
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}
}
