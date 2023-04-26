package com.mobi.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobi.Constants.ErrorConstants;
import com.mobi.exceptions.CustomError;
import com.mobi.models.Questions;
import com.mobi.models.TestInfo;
import com.mobi.models.TestQuestions;

@Service
@Transactional
public class TestService {

	@Autowired
	QuestionsService questionsService;

	@Autowired
	TestInfoService testInfoService;
	
	@Autowired
	TestQuestionsService testQuestionsService;

	public ResponseEntity<?> createTests(TestInfo testInfo) {
		testInfoService.save(testInfo);
		testInfo.getQuestions().stream().forEach(q -> q.setTestsId(testInfo.getId())); 
		testInfoService.save(testInfo);
		HttpStatus code = HttpStatus.CREATED;
		CustomError body = new CustomError(new Date(), code.toString(), testInfo);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> findTestByTestId(Integer testId) {

		ArrayList<Questions> generatedTest = new ArrayList<>();
		Questions questions = null;

		if (testInfoService.findByTestId(testId) == null) {
			HttpStatus code = HttpStatus.NOT_FOUND;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
			return ResponseEntity.status(code).body(body);
		}

		if (testInfoService.findByTestId(testId).get().isPublishStatus()) {
			for (Integer tId : randomSetGenerator(testId)) {
				questions = new Questions();
				questions.setQuestionId(tId);
				CustomError responseObj = (CustomError) questionsService.getQuestionById(tId).getBody();
				Questions quest = (Questions) responseObj.getResponse();
				questions.setQuestion(quest.getQuestion());
				questions.setQuestionType((quest.getQuestionType()));
				questions.setQuestionCategory((quest.getQuestionCategory()));
				if (!questions.getQuestionType().equals("Fill up")) {
					questions.setOptionss(quest.getOptionss());
				}

				generatedTest.add(questions);

			}
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), generatedTest);
			return ResponseEntity.status(code).body(body);
		}
		HttpStatus code = HttpStatus.NOT_FOUND;
		CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_PUBLISHED);
		return ResponseEntity.status(code).body(body);
	}

	private List<Integer> randomSetGenerator(Integer testId) {

		Random rand = new Random();
		List<Integer> testQuestionList = new ArrayList<>();
		testQuestionsService.findByTestId(testId).stream()
				.forEach(tempQId -> testQuestionList.add(tempQId.getQuestionId()));
		int questionListSize = testQuestionList.size();

		{
			List<Integer> newList = new ArrayList<>();
			for (int questionIterator = 0; questionIterator < questionListSize; questionIterator++) {

				int randomIndex = rand.nextInt(testQuestionList.size());

				newList.add(testQuestionList.get(randomIndex));

				testQuestionList.remove(randomIndex);
			}

			// System.out.println(newList);
			return newList;
		}

	}

	public ResponseEntity<?> findallTests() {
		System.out.println(testInfoService.findById(93).get().getQuestions());
		List<TestInfo> tests = new ArrayList<>();
		testInfoService.findAll().forEach(tests::add);
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(), tests);
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<?> deleteTest(Integer testId) throws SQLIntegrityConstraintViolationException {
		try {
			testInfoService.findByTestId(testId).get().getDesciption();
			testInfoService.deleteByTestId(testId);

			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_DELETED);
			return ResponseEntity.status(code).body(body);
		} catch (Exception e) {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(), ErrorConstants.TEST_NOT_EXIST);
			return ResponseEntity.status(code).body(body);
		}
	}

	public ResponseEntity<?> updateTest(TestInfo info) {

		Optional<TestInfo> testData = testInfoService.findById(info.getId());
		
		if (testData.isPresent()) {
			for (TestQuestions testQuestionsInDB : info.getQuestions()) {
				for (TestQuestions testQuestionsToAdd : testData.get().getQuestions()) {
					if (testQuestionsInDB.getQuestionId() == testQuestionsToAdd.getQuestionId()) {
						HttpStatus code = HttpStatus.BAD_REQUEST;
						CustomError body = new CustomError(new Date(), code.toString(),
								"Duplicate Question found. (Question ID :" + testQuestionsToAdd.getQuestionId()
										+ ")");
						return ResponseEntity.status(code).body(body);
					}
				}

			}

			testInfoService.save(info);
			HttpStatus code = HttpStatus.OK;
			CustomError body = new CustomError(new Date(), code.toString(),
					ErrorConstants.TEST_UPDATED + ".ID :" + info.getId());
			return ResponseEntity.status(code).body(body);
		} else {
			HttpStatus code = HttpStatus.BAD_REQUEST;
			CustomError body = new CustomError(new Date(), code.toString(),
					ErrorConstants.TEST_NOT_UPDATED + info.getId());
			return ResponseEntity.status(code).body(body);
		}
	}

}
