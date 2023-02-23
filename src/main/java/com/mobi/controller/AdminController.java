package com.mobi.controller;

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
import com.mobi.models.Optionss;
import com.mobi.models.Questions;
import com.mobi.service.AnswersService;
import com.mobi.service.QuestionsService;
import com.mobi.service.UserService;

@RestController
public class AdminController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	UserService userService;

	@Autowired
	AnswersService answersService;

	@GetMapping("/questions")
	public List<Questions> getAllQuestions() {
		return questionsService.getAllQuestions();
	}

	@GetMapping("/question/{id}")
	public Questions getQuesionById(@PathVariable Integer id) {
		return questionsService.getQuestionById(id);
	}

	@PostMapping("/question")
	public ResponseEntity<String> addQuestion(@RequestBody Questions questions) {
		for (Optionss q : questions.getOptionss()) {
			if (q.getOptionValue().equals(questions.getAnswers().getCorrectAnswer())) {
				questionsService.addQuestion(questions);
				return ResponseEntity.ok().body("Question Added");
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correct Option Not Found");
	}
	
	@PutMapping("/questions/{id}")
	public void updateQuestion(@RequestBody Questions questions, @PathVariable Integer id) {
		questionsService.updateQuestion(questions,id);
	}

	@DeleteMapping("/question/{id}")
	public void deleteQuestion(@PathVariable Integer id) {
		questionsService.deleteQuestion(id);
	}
	
	@GetMapping("/questions/{type}")
	public List<Questions> fingByType(@PathVariable String type) {
		return questionsService.fingByType(type);
	}

}
