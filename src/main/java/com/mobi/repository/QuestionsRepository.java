package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Questions;

@Service
public interface QuestionsRepository extends CrudRepository<Questions, Integer>{

	List<Questions> findByQuestionType(String questionType);
}
