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
	private User user;

	public Admin(long id) {
		this.id = id;
	}
	public Admin() {
		id=0;
		user=new User();
	}

	public User getUser() {
		return user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

