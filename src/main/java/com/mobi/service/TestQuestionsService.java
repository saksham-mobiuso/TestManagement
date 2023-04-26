package com.mobi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.models.TestQuestions;
import com.mobi.repository.TestQuestionsRepository;

@Service
public class TestQuestionsService {
	
	@Autowired
	TestQuestionsRepository testQuestionsRepository;

	public Collection<TestQuestions> findByTestId(Integer testId) {
		return testQuestionsRepository.findByTestId(testId);
	}

}
