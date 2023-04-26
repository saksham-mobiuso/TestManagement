package com.mobi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobi.Constants.ErrorConstants;
import com.mobi.exceptions.CustomError;
import com.mobi.models.Questions;
import com.mobi.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	public ResponseEntity<CustomError> getAllQuestions() {
		List<Questions> questions = new ArrayList<>();
		questionsRepository.findAll().forEach(questions::add);
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), questions);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> getQuestionById(Integer id) {

		if (!questionsRepository.findById(id).isEmpty()) {
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), questionsRepository.findById(id).stream()
					.filter(quest -> quest.getQuestionId().equals(id)).findFirst().get());
			return ResponseEntity.status(code).body(body);
		} else {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.QUESTION_NOT_EXIST);
			return ResponseEntity.status(code).body(body);
		}

	}

	public ResponseEntity<?> addQuestion(Questions questions) {
		if (!questionsRepository.findById(questions.getQuestionId()).isEmpty()) {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.QUESTION_ALREADY_EXIST);
			return ResponseEntity.status(code).body(body);
		} else {
			HttpStatus code = HttpStatus.CREATED;
			CustomError body = new CustomError(new Date(), code.toString(), questionsRepository.save(questions));
			return ResponseEntity.status(code).body(body);
		}
	}

	public ResponseEntity<?> deleteQuestion(Integer id) {
		try {
			questionsRepository.deleteById(id);
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.QUESTION_DELETED);
			return ResponseEntity.status(code).body(body);
		} catch (Exception e) {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.QUESTION_NOT_EXIST);
			return ResponseEntity.status(code).body(body);
		}
	}

	public ResponseEntity<?> updateQuestion(Questions questions) {
		questionsRepository.deleteById(questions.getQuestionId());
		questionsRepository.save(questions);

		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), questions);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<CustomError> findByType(String type) {
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), questionsRepository.findByQuestionType(type));
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<CustomError> findByCategory(String category) {
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(),
				questionsRepository.findByQuestionCategory(category));
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> findByQuestionTypeOrQuestionCategory(String type, String category) {
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(),
				questionsRepository.findByQuestionTypeOrQuestionCategory(type, category));
		return ResponseEntity.status(code).body(body);
	}

}
