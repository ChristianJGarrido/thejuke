package com.netbuilder.thejuke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Genre")
public class Genre {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	
	public Genre(String name){
		this.name = name;
	}
	
	public Genre(){
		name = "Not Found";
	}
	
	public String toString(){
		final StringBuilder builder = new StringBuilder();
		builder.append("Genre {");
		builder.append("id='").append(id).append("', ");
		builder.append("name='").append(name).append("'}");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
