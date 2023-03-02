package com.mobi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.exceptions.CustomError;
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
	
	@GetMapping("/usertests/{testId}")
	public ResponseEntity<Object> getUserTest(@PathVariable Integer testId, Authentication auth) {
		return myUserService.getUserTest(testId,auth);
	}
	
	@PostMapping("/usertest")
	public ResponseEntity<CustomError> assignTest(@RequestBody User user){
		return myUserService.assignTest(user);
	}
	
	@GetMapping("/usertests")
	public List<Integer> listAllAssignedTestId(Authentication auth) {
		return myUserService.listAllAssignedTestId(auth);
	}

}
