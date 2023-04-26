package com.mobi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mobi.exceptions.CustomError;
import com.mobi.models.Questions;
import com.mobi.models.User;
import com.mobi.service.MyUserService;
import com.mobi.service.TestRecordsService;


@RestController
public class UserController {
	
	@Autowired
	MyUserService myUserService;
	
	@Autowired
	TestRecordsService testRecordsService;
	
	@GetMapping("/users")
	public ResponseEntity<CustomError> getAllUsers() {
		return myUserService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		return myUserService.addUser(user);
	}
	
	@GetMapping("/usertests/{testId}")
	public ResponseEntity<?> getUserTest(@PathVariable Integer testId, Authentication auth) {
		return myUserService.getUserTest(testId,auth);
	}
	
	@PostMapping("/usertests")
	public ResponseEntity<?> assignTest(@RequestBody User user){
		return myUserService.assignTest(user);
	}
	
	@GetMapping("/usertests")
	public ResponseEntity<?> listAllAssignedTestId(Authentication auth) {
		return myUserService.listAllAssignedTestId(auth);
	}
	
	@PutMapping("/usertests")
	public ResponseEntity<?> assignMoreTest(@RequestBody User user) {
		return myUserService.assignMoreTest(user);
	}
	
	@PostMapping("/usertests/{testId}")
	public ResponseEntity<?> takeTest(@PathVariable Integer testId ,@RequestBody List<Questions> test,Authentication auth) {
		return myUserService.takeTest(testId,test,auth);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<?> deleteUser(@RequestParam String username){
		return myUserService.deleteUser(username);
	}
	
	@GetMapping("/user/results")
	public ResponseEntity<CustomError> testResult(Authentication auth) {
		return testRecordsService.testResults(auth);
	}
	
	@GetMapping("/users/results")
	public ResponseEntity<CustomError> allTestResult(@RequestParam(name="q", required = false) String username) {
		if(username!=null) {
			return testRecordsService.findByUserName(username);
		}
		return testRecordsService.allTestResult();
	}
}
