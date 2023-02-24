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

	public ResponseEntity<Questions> updateQuestion(Questions questions, Integer id) {
		Optional<Questions> questionData = questionsRepository.findById(id);
		if(questionData.isPresent()) {
			Questions tempQuestion = questionData.get();
			tempQuestion.setQuestion(questions.getQuestion());
			tempQuestion.setOptionss(questions.getOptionss());
			tempQuestion.setQuestionType(questions.getQuestionType());
			return new ResponseEntity<>(questionsRepository.save(tempQuestion),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public List<Questions> fingByType(String type) {
		return questionsRepository.findByQuestionType(type);
	}
}
