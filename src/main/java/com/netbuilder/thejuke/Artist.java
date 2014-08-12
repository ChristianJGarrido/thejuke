package com.netbuilder.thejuke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "bio")
	private String bio;
	
	public Artist(String name, String bio){
		this.name = name;
		this.bio = bio;
	}
	
	public Artist(){
		this.name = "Unknown Name";
		this.bio = "Empty Bio";
	}
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Artist {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name);
		sb.append("bio='").append(bio).append("', ");
		sb.append('}');
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
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
}
