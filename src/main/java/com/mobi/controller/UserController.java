package com.mobi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.models.User;
import com.mobi.service.MyUserService;

@RestController
public class UserController {
	
	@Autowired
	MyUserService myUserService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return myUserService.getAllUsers();
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		myUserService.addUser(user);		
	}

}
