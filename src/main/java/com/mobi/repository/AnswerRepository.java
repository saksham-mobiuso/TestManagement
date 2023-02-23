package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Answers;

@Service
public interface AnswerRepository extends CrudRepository<Answers,Integer>{
	
	 List<Answers> findByQuestionId(Integer questionId);
	 

}
