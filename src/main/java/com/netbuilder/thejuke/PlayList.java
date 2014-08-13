package com.netbuilder.thejuke;

import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "Playlist")
public class PlayList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "Admin_id")
	private long adminId;
	
	@Column(name = "name")
	private String name;
	
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name="Playlist_has_Song",
	    		joinColumns=
	            @JoinColumn(name="Playlist_id", referencedColumnName="id"),
	            inverseJoinColumns=
	            @JoinColumn(name="Song_id", referencedColumnName="id")
	    )
	private List<Song> songList;

	public long getId() {
		return id;
	}

	public PlayList()
	{
		this.name = "Unknown Name";
		this.adminId = -1L;
		this.songList = null;
	}
	
	public PlayList(String name, List<Song> songList, long adminId) {
		
		this.adminId = adminId;
		this.name = name;
		this.songList = songList;
		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Playlist {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name);
		sb.append("'\n");
		for(Song s: songList) {
			
			sb.append(s + " ");
		}
		//sb.append("ong='").append(bio).append("'}");
		return sb.toString();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
