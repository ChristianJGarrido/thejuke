package com.netbuilder.thejuke;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="lbum")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	@Column(name = "producer")
	private String producer;
//	@Column(name = "dateCreated")
//	private Date dateCreated;
//	private ImageIcon albumArt;
	public Album(String name, String producer)
	{
		this.name = name;
		this.producer = producer;
	}
	
	public Album()
	{
		this.name = "Unknown Name";
		this.producer = "Unknown Producer";
	}
	
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("Album {");
		sb.append("id='").append(id).append("', ");
		sb.append("name='").append(name);
		sb.append("producer='").append(producer).append("', ");
		sb.append('}');
		return sb.toString();
		
	}
}
