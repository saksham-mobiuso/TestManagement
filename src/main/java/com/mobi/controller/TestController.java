package com.mobi.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.Constants.ErrorConstants;
import com.mobi.exceptions.CustomError;
import com.mobi.models.TestInfo;
import com.mobi.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService testService;

	@PostMapping("/tests")
	public ResponseEntity<?> createTest(@RequestBody TestInfo testInfo) {
		return testService.createTests(testInfo);		
	}

	@GetMapping("/tests/{testId}")
	public ResponseEntity<?> findTestByTestId(@PathVariable Integer testId) {
		return testService.findTestByTestId(testId);
	}

	@GetMapping("/tests")
	public ResponseEntity<?> allTests() {
		return testService.findallTests();
	}

	@DeleteMapping("/tests/{testId}")
	public ResponseEntity<?> deleteTest(@PathVariable Integer testId) {
		try {
			return testService.deleteTest(testId);
		} catch (Exception e) {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_DELETE_FAILURE);
			return ResponseEntity.status(code).body(body);
		}
	}
	
	@PutMapping("/tests")
	public ResponseEntity<?> updateTest(@RequestBody TestInfo info) {
		return testService.updateTest(info);
	}

}
