package com.netbuilder.thejuke.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.persistence.CascadeType;
@Entity
@NamedQueries({
		@NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.userName = :name"),
		@NamedQuery(name = User.FIND_BY_PASSWORD, query = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password"),
		@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u") })
@Table(name = "User")
public class User {

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "userName", nullable = false, length = 45)
	@NotNull
	private String userName;

	@Column(name = "passWord", nullable = false, length = 45)
	@NotNull
	private String password;
	
	@Column(name = "balance")
	@NotNull
	private float balance;
	
	private boolean admin = false;

	public static final String FIND_BY_USERNAME = "User.findByUsername";
	public static final String FIND_BY_PASSWORD = "User.findByPassword";
	public static final String FIND_ALL = "User.findAll";

	public User(String userName, String passWord,float balance) {

		this.userName = userName;
		this.password = passWord;
		this.balance=balance;
	}

	public User() {
		//id = 0;
		userName = "not set";
		password = "";
		balance = 0;
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
		return password;
	}

	public void setPassWord(String passWord) {
		this.password = passWord;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("User {");
		sb.append("id='").append(id).append("', ");
		sb.append("userNamee='").append(userName).append("', ");
		sb.append("passWord='").append(password).append("', ");
		sb.append("balance='").append(balance).append("'}\n");
		return sb.toString();

	}

	public void update(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassWord();
		this.balance = user.getBalance();
	}
	/**Attempts to subtract a given amount from the balance. Returns true if it succeeds. False otherwise. **/
	public boolean subtractFrombalance(float price)
	{
		if(balance-price>=0)
		{
			balance=balance-price;
			return true;
		}
		else
		{
			return false;
		}
	}

}
