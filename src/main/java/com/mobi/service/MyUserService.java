package com.mobi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.mobi.Constants.ErrorConstants;
import com.mobi.exceptions.CustomError;
import com.mobi.models.Questions;
import com.mobi.models.TestInfo;
import com.mobi.models.TestRecords;
import com.mobi.models.User;
import com.mobi.repository.UserRepository;

@Service
@Transactional
public class MyUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TestService testService;

	@Autowired
	TestInfoService testInfoService;
	
	@Autowired
	OptionsService optionsService;

	@Autowired
	QuestionsService questionsService;
	
	@Autowired
	TestRecordsService testRecordsService;

	public ResponseEntity<CustomError> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), users);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> addUser(User user) {

		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPassword = user.getUserPassword();
			user.setUserPassword(encoder.encode(rawPassword));
			HttpStatus code = HttpStatus.CREATED;
			CustomError body = new CustomError(new Date(), code.toString(), userRepository.save(user));
			return ResponseEntity.status(code).body(body);
		} catch (Exception e) {
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.USER_ALREADY_EXIST);
			return ResponseEntity.status(code).body(body);
		}

	}

	public ResponseEntity<?> getUserTest(Integer testId, Authentication auth) {
		String currentUserName = auth.getName();
		List<Integer> userTestIdList = new ArrayList<>();
		userRepository.findByUserName(currentUserName).getUserTests().forEach(t -> userTestIdList.add(t.getId()));

		if (userTestIdList.contains(testId)) {
			ResponseEntity<?> body = testService.findTestByTestId(testId);
			return body;
		}
		HttpStatus code = HttpStatus.NOT_FOUND;
		CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> assignTest(User user) {

		User user_data = userRepository.findByUserName(user.getUserName());
		List<Integer> testIds = new ArrayList<Integer>();
		user.getUserTests().forEach(testid -> testIds.add(testid.getId()));
		user.setUserId(user_data.getUserId());
		user.setEnabled(user_data.isEnabled());
		user.setRole(user_data.getRole());
		user.setUserPassword(user_data.getUserPassword());
		for (Integer id : testIds) {
			if (!testInfoService.findById(id).isPresent()
					|| !testInfoService.findById(id).get().isPublishStatus()) {
				HttpStatus code = HttpStatus.NOT_FOUND;
				CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
				return ResponseEntity.status(code).body(body);
			}
			for (int userTestId = 0; userTestId < user_data.getUserTests().size(); userTestId++) {
				if (testIds.contains(user_data.getUserTests().get(userTestId).getId())) {
					HttpStatus code = HttpStatus.BAD_REQUEST;
					CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_ALREADY_ASSIGNED);
					return ResponseEntity.status(code).body(body);
				}
			}
		}
		userRepository.save(user);
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), user_data.getUserTests());
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<CustomError> listAllAssignedTestId(Authentication auth) {
		String currentUserName = auth.getName();
		List<TestInfo> userTestIdList = new ArrayList<>();
		userRepository.findByUserName(currentUserName).getUserTests().forEach(
				t -> userTestIdList.add(new TestInfo(testInfoService.findByTestId(t.getId()).get().getId(),
						testInfoService.findByTestId(t.getId()).get().getDesciption(), true, null))); 
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), userTestIdList);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> assignMoreTest(User user) {
		User userData = userRepository.findByUserName(user.getUserName());
		user.setUserId(userData.getUserId());
		user.setEnabled(userData.isEnabled());
		user.setRole(userData.getRole());
		user.setUserPassword(userData.getUserPassword());

		List<Integer> testIds = new ArrayList<Integer>();
		user.getUserTests().forEach(testid -> testIds.add(testid.getId()));
		for (Integer id : testIds) {
			if (!testInfoService.findById(id).isPresent()
					|| !testInfoService.findById(id).get().isPublishStatus()) {
				HttpStatus code = HttpStatus.NOT_FOUND;
				CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
				return ResponseEntity.status(code).body(body);
			}
		}
		for (int usesrTestIterator = 0; usesrTestIterator < userData.getUserTests().size(); usesrTestIterator++) {
			if (testIds.contains(userData.getUserTests().get(usesrTestIterator).getId())) {
				HttpStatus code = HttpStatus.BAD_REQUEST;
				CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_ALREADY_ASSIGNED);
				return ResponseEntity.status(code).body(body);
			}
		}

		userData.getUserTests().addAll(user.getUserTests());
		user.setUserTests(userData.getUserTests());
		userRepository.save(user);

		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), user.getUserTests());
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> takeTest(Integer testId, List<Questions> test, Authentication auth) {
		int score = 0;
		String currentUser = auth.getName();
		List<TestInfo> userTests = userRepository.findByUserName(currentUser).getUserTests();

		for (TestInfo testInfo : userTests) {
			if (testInfo.getId().equals(testId)) {
				for (Questions question : test) {
					if (optionsService.findByQuestionIdAndCorrectAnswerTrue(question.getQuestionId())
							.getOptionValue().equalsIgnoreCase(question.getUserAnswer())) {
						score++;
					}
				}
				TestRecords testReport = new TestRecords(currentUser, new Date(), testId, testInfo.getDesciption(),
						score + " out of " + test.size());
				testRecordsService.save(testReport);

				HttpStatus code = HttpStatus.OK;
				CustomError body = new CustomError(new Date(), code.toString(), testReport);
				return ResponseEntity.status(code).body(body);
			}
		}
		HttpStatus code = HttpStatus.NOT_FOUND;
		CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> deleteUser(String username) {
		if (userRepository.findByUserName(username) != null) {
			userRepository.deleteByUserName(username);
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.USER_DELETED);
			return ResponseEntity.status(code).body(body);
		} else {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.USER_NOT_FOUND);
			return ResponseEntity.status(code).body(body);
		}
	}
}
