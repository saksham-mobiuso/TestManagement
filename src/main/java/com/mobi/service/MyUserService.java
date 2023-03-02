package com.mobi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mobi.exceptions.CustomError;
import com.mobi.models.User;
import com.mobi.repository.TestInfoRepository;
import com.mobi.repository.UserRepository;

@Service
public class MyUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TestService testService;

	@Autowired
	TestInfoRepository testInfoRepository;

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

	public ResponseEntity<Object> getUserTest(Integer testId, Authentication auth) {
		String currentUserName = auth.getName();
		List<Integer> userTestIdList = new ArrayList<>();
		userRepository.findById(currentUserName).get().getUserTests().forEach(t -> userTestIdList.add(t.getTestId()));

		if (userTestIdList.contains(testId)) {
			return testService.findTestByTestId(testId);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(new Date(), "Test does not exist"));
	}

	public ResponseEntity<CustomError> assignTest(User user) {

		User user_data = userRepository.findById(user.getUserId()).get();
		Integer testId = user.getUserTests().get(0).getTestId();
		if (testInfoRepository.findById(testId).get().isPublishStatus()) {
			user.setEnabled(user_data.isEnabled());
			user.setRole(user_data.getRole());
			user.setUserPassword(user_data.getUserPassword());
			userRepository.save(user);
			return null;
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(new Date(), "Test Not Published"));
	}

	public List<Integer> listAllAssignedTestId(Authentication auth) {
		String currentUserName = auth.getName();
		List<Integer> userTestIdList = new ArrayList<>();
		userRepository.findById(currentUserName).get().getUserTests().forEach(t -> userTestIdList.add(t.getTestId()));
		return userTestIdList;
	}

}
