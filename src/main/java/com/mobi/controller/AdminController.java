package com.mobi.controller;

import java.util.Date;
import java.util.List;
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
import com.mobi.exceptions.CustomError;
import com.mobi.models.Optionss;
import com.mobi.models.Questions;
import com.mobi.service.QuestionsService;

@RestController
public class AdminController {

	@Autowired
	private QuestionsService questionsService;


	@GetMapping("/questions")
	public List<Questions> getAllQuestions() {
		return questionsService.getAllQuestions();
	}

	@GetMapping("/questions/{id}")
	public Questions getQuesionById(@PathVariable Integer id) {
		return questionsService.getQuestionById(id);
	}

	@PostMapping("/questions")
	public ResponseEntity<CustomError> addQuestion(@RequestBody Questions questions) {
		for (Optionss q : questions.getOptionss()) {
			if (q.isCorrectAnswer()) {
				questionsService.addQuestion(questions);
				return ResponseEntity.ok().body(null);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(new Date(),"No Correct Option Found"));
	}
	
	@PutMapping("/questions")
	public ResponseEntity<CustomError> updateQuestion(@RequestBody Questions questions) {
		try {
			questionsService.updateQuestion(questions);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(new Date(),"Question not found"));
		}
		return null;
	}

	@DeleteMapping("/questions/{id}")
	public void deleteQuestion(@PathVariable Integer id) {
		questionsService.deleteQuestion(id);
	}
	
	@GetMapping("/questions/type/{type}")
	public List<Questions> fingByType(@PathVariable String type) {
		return questionsService.fingByType(type);
	}

}
