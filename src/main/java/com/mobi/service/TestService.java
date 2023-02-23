package com.mobi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobi.models.Questions;
import com.mobi.models.Tests;
import com.mobi.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	QuestionsService questionsService;

	@Autowired
	TestRepository testRepository;

	public ArrayList<Questions> createTests(List<Integer> id) {

		ArrayList<Questions> generatedTest = new ArrayList<>();

		Questions question = null;

		int randomTestId = (int) (Math.random() * 300);

		Tests test = null;

		for (int i : id) {

			question = new Questions();
			test = new Tests();

			question.setQuestionId(questionsService.getQuestionById(i).getQuestionId());
			question.setQuestion(questionsService.getQuestionById(i).getQuestion());
			question.setOptionss(questionsService.getQuestionById(i).getOptionss());

			test.setTestId(randomTestId);
			test.setTestQuestionId(i);

			testRepository.save(test);

			generatedTest.add(question);
		}

		return generatedTest;
	}

	public ArrayList<Questions> findTestByTestId(Integer testId) {
		testRepository.findByTestId(testId).stream().forEach(t -> System.out.println(t.getTestQuestionId()));

		ArrayList<Questions> generatedTest = new ArrayList<>();
		List<Tests> tempList = testRepository.findByTestId(testId);
		Questions questions = null;

		for (Tests t : tempList) {
			questions = new Questions();
			questions.setQuestionId(t.getTestQuestionId());
			questions.setQuestion(questionsService.getQuestionById(t.getTestQuestionId()).getQuestion());
			questions.setOptionss(questionsService.getQuestionById(t.getTestQuestionId()).getOptionss());
			generatedTest.add(questions);
		}
		return generatedTest;
	}

}