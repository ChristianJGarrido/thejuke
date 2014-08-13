package com.netbuilder.thejuke;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="User_id", referencedColumnName="id")
	private long userId;

	public Admin(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

