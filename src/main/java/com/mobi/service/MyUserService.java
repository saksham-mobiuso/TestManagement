package com.mobi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobi.models.User;
import com.mobi.repository.UserRepository;

@Service
public class MyUserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public void addUser(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = user.getUserPassword();
		user.setUserPassword(encoder.encode(rawPassword));
		System.out.println(user.getUserPassword());
		userRepository.save(user);

	}
	

}
