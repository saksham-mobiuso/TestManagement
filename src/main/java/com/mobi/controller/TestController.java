package com.mobi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mobi.models.Questions;
import com.mobi.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	@PostMapping("/test")
	public ArrayList<Questions> createTest(@RequestParam List<Integer> id) {
		return testService.createTests(id);
	}
	
	@GetMapping("/tests/{testId}")
	public List<Questions> findTestByTestId(@PathVariable Integer testId ) {
		System.out.println("hwlo " + testId);
		return testService.findTestByTestId(testId);
	}
	

}
