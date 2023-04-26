package com.mobi.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mobi.exceptions.CustomError;
import com.mobi.models.Optionss;
import com.mobi.models.Questions;
import com.mobi.service.QuestionsService;

@RestController
public class QuestionsController {

	@Autowired
	private QuestionsService questionsService;


	@GetMapping("/questions")
	public ResponseEntity<?> getAllQuestions(@RequestParam(name = "type",required = false) String type,
			@RequestParam(name = "category",required = false) String category) {
		if(type!=null || category!=null) {
			return questionsService.findByQuestionTypeOrQuestionCategory(type,category);
		}		
		return questionsService.getAllQuestions();
	}

	@GetMapping("/questions/{id}")
	public ResponseEntity<?> getQuesionById(@PathVariable Integer id) {
		return questionsService.getQuestionById(id);
	}

	@PostMapping("/questions")
	public ResponseEntity<?> addQuestion(@RequestBody Questions questions) {
		for (Optionss option : questions.getOptionss()) {
			if (option.isCorrectAnswer()) {
				return questionsService.addQuestion(questions);
			}
		}
		HttpStatus code = HttpStatus.NOT_FOUND;
		CustomError body = new CustomError(new Date(),code.toString(),"No Correct Option Found");
		return ResponseEntity.status(code).body(body);
	}
	
	@PutMapping("/questions")
	public ResponseEntity<?> updateQuestion(@RequestBody Questions questions) {
		try {
			return questionsService.updateQuestion(questions);
		} catch (Exception e) {
			HttpStatus code = HttpStatus.NOT_FOUND;
			CustomError body = new CustomError(new Date(),code.toString(),"Question not found");
			return ResponseEntity.status(code).body(body);
		}
	}

	@DeleteMapping("/questions/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Integer id) {
		return questionsService.deleteQuestion(id);
	}

}
