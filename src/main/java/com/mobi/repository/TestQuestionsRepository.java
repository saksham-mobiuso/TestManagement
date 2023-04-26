package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mobi.models.TestQuestions;

public interface TestQuestionsRepository extends CrudRepository<TestQuestions, Integer>{
	
	List<TestQuestions> findByTestId(Integer testId);
}
