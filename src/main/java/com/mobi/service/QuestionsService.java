package com.mobi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mobi.models.Questions;
import com.mobi.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	public List<Questions> getAllQuestions() {
		List<Questions> questions = new ArrayList<>();
		questionsRepository.findAll().forEach(questions::add);
		return questions;
	}

	public Questions getQuestionById(Integer id) {
		return questionsRepository.findById(id).stream().filter(t -> t.getQuestionId().equals(id)).findFirst().get();
	}

	public void addQuestion(Questions questions) {
		questionsRepository.save(questions);
	}

	public void deleteQuestion(Integer id) {
		questionsRepository.deleteById(id);
	}

	public void updateQuestion(Questions questions) {
		questionsRepository.deleteById(questions.getQuestionId());
		questionsRepository.save(questions);
	}

	public List<Questions> fingByType(String type) {
		return questionsRepository.findByQuestionType(type);
	}

}
