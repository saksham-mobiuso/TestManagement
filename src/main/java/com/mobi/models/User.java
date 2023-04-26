package com.mobi.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer userId;
	@Column(unique = true,name = "username")
	private String userName;
	@Column(name = "userpassword")
	private String userPassword;
	private String role;
	private boolean enabled;
	
	@ManyToMany
	private List<TestInfo> userTests;
	
	public User() {
		
	}
	public User(Integer userId, String userName, String userPassword, String role, boolean enabled,
			List<TestInfo> userTests) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.role = role;
		this.enabled = enabled;
		this.userTests = userTests;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<TestInfo> getUserTests() {
		return userTests;
	}

	public void setUserTests(List<TestInfo> userTests) {
		this.userTests = userTests;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
