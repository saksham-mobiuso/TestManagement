package com.mobi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobi.exceptions.CustomError;
import com.mobi.models.Questions;
import com.mobi.models.TestInfo;
import com.mobi.repository.TestInfoRepository;
import com.mobi.repository.TestQuestionsRepository;

@Service
public class TestService {

	@Autowired
	QuestionsService questionsService;

	@Autowired
	TestInfoRepository testInfoRepository;

	@Autowired
	TestQuestionsRepository testQuestionsRepository;

	public ResponseEntity<CustomError> createTests(TestInfo testInfo) {
		int randomTestId = (int) (Math.random() * 300);
		//int randomTestId = 1;
		if (testInfoRepository.findByTestId(randomTestId) == null) {
			testInfo.setTestId(randomTestId);
			testInfo.getTestQuestions().stream().forEach(q -> q.setTestId(randomTestId));
			testInfoRepository.save(testInfo);
			return ResponseEntity.ok().body(null);
		} else {
			System.out.println("test exist");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new CustomError(new Date(), "ID already exist, Try Again"));
		}

	}

	public ResponseEntity<Object> findTestByTestId(Integer testId) {

		ArrayList<Questions> generatedTest = new ArrayList<>();

		Questions questions = null;

		if (testInfoRepository.findByTestId(testId).isPublishStatus()) {

			for (Integer t : randomSetGenerator(testId)) {
				questions = new Questions();
				questions.setQuestionId(t);
				questions.setQuestion(questionsService.getQuestionById(t).getQuestion());
				questions.setQuestionType((questionsService.getQuestionById(t).getQuestionType()));
				if (!questions.getQuestionType().equals("Fill up")) {
					questions.setOptionss(questionsService.getQuestionById(t).getOptionss());
				}

				generatedTest.add(questions);
			}
			return ResponseEntity.status(HttpStatus.OK).body(generatedTest);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(new Date(), "Not Published"));

	}

	private List<Integer> randomSetGenerator(Integer testId) {

		Random rand = new Random();
		List<Integer> testQuestionList = new ArrayList<>();
		testQuestionsRepository.findByTestId(testId).stream()
				.forEach(tempQId -> testQuestionList.add(tempQId.getTestQuestionsId()));
		int questionListSize = testQuestionList.size();

		{
			List<Integer> newList = new ArrayList<>();
			for (int i = 0; i < questionListSize; i++) {

				int randomIndex = rand.nextInt(testQuestionList.size());

				newList.add(testQuestionList.get(randomIndex));

				testQuestionList.remove(randomIndex);
			}
		
			System.out.println(newList);
			return newList;
		}

	}

	public List<TestInfo> findallTests() {
		List<TestInfo> tests = new ArrayList<>();
		testInfoRepository.findAll().forEach(tests::add);
		return tests;
	}

	public void deleteTest(Integer testId) {
		testInfoRepository.deleteById(testId);
	}

	public void updateTest(Integer testId, TestInfo info) {
		Optional<TestInfo> testData = testInfoRepository.findById(testId);
		if (testData.isPresent()) {
			testInfoRepository.save(info);
		} 
	}

}
