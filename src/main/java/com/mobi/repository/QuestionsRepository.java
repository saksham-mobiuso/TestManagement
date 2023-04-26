package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Questions;

@Service
public interface QuestionsRepository extends CrudRepository<Questions, Integer>{

	List<Questions> findByQuestionType(String questionType);
	Questions findByQuestionId(Integer questionId);
	List<Questions> findByQuestionCategory(String questionCategory);
	List<Questions> findByQuestionTypeOrQuestionCategory(String questionType,String questionCategory);
}
