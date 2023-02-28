package com.mobi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mobi.exceptions.CustomError;
import com.mobi.models.TestInfo;
import com.mobi.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService testService;

	@PostMapping("/tests")
	public ResponseEntity<CustomError> createTest(@RequestBody TestInfo testInfo) {
		return testService.createTests(testInfo);		
	}

	@GetMapping("/tests/{testId}")
	public ResponseEntity<Object> findTestByTestId(@PathVariable Integer testId) { 
		System.out.println("hwlo " + testId);
		return testService.findTestByTestId(testId);
	}

	@GetMapping("/tests")
	public List<TestInfo> allTests() {
		return testService.findallTests();
	}

	@DeleteMapping("/tests/{testId}")
	public void deleteTest(@PathVariable Integer testId) {
		testService.deleteTest(testId);
	}
	
	@PutMapping("/tests/{testId}")
	public void updateTest(@PathVariable Integer testId,@RequestBody TestInfo info) {
		testService.updateTest(testId, info);
	}

}
