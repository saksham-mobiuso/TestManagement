package com.mobi.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_data")
public class User {
	
	@Id
	private String userId;
	private String userPassword;
	private String role;
	private boolean enabled;
	
	@ManyToMany
	private List<TestInfo> userTests;
	
	public User() {
		
	}
	
	
	public User(String userId, String userPassword, String role, boolean enabled, List<TestInfo> userTests) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.role = role;
		this.enabled = enabled;
		this.userTests = userTests;
	}

	public List<TestInfo> getUserTests() {
		return userTests;
	}

	public void setUserTests(List<TestInfo> userTests) {
		this.userTests = userTests;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
