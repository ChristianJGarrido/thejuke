package com.netbuilder.thejuke;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="userName", nullable = false, length = 45)
	@NotNull
	private String userName;
	
	@Column(name="passWord", nullable = false, length = 45)
	@NotNull
	private String passWord;
	
	@Column(name = "balance")
	@NotNull
	private float balance;
	
	public User(String userName, String passWord) {
		
		this.userName =  userName;
		this.passWord = passWord;
	}	
	public User() {
		id=0;
		userName="not set";
		passWord="";
		balance=0;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("User {");
		sb.append("id='").append(id).append("', ");
		sb.append("userNamee='").append(userName).append("', ");
		sb.append("passWord='").append(passWord).append("', ");
		sb.append("balance='").append(balance).append("'}\n");
		return sb.toString();
		
	}

}
