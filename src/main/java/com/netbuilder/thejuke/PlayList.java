package com.netbuilder.thejuke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public long getId() {
		return id;
	}

	public PlayList() {
		
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
