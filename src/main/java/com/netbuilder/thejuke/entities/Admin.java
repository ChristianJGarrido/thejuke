package com.netbuilder.thejuke.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(optional = false, cascade=CascadeType.ALL)
	@JoinColumn(name = "User_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "adminId", cascade = CascadeType.ALL)
	private List<PlayList> plList;

	public List<PlayList> getPlList() {
		return plList;
	}

	public void setPlList(List<PlayList> plList) {
		this.plList = plList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin(User user) {
		this.user = user;
	}

	public Admin() {
		//id = 0;
		user = new User();
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Admin {");
		sb.append("id='").append(id).append("', ");
		sb.append("user_id='").append(user.getId()).append("'}");
		return sb.toString();
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

	public void update(Admin admin) {
		this.user = admin.getUser();
	}
}
