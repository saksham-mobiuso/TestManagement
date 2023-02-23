package com.mobi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

		ArrayList<Questions> generatedTest = new ArrayList<>();

		Questions questions = null;

		for (Integer t : randomSetGenerator(testId)) {
			questions = new Questions();
			questions.setQuestionId(t);
			questions.setQuestion(questionsService.getQuestionById(t).getQuestion());
			questions.setQuestionType((questionsService.getQuestionById(t).getQuestionType()));
			questions.setOptionss(questionsService.getQuestionById(t).getOptionss());
			generatedTest.add(questions);
		}
		return generatedTest;
	}

	public List<Integer> randomSetGenerator(Integer testId) {

		Random rand = new Random();
		List<Integer> testQuestionList = new ArrayList<>();
		testRepository.findByTestId(testId).stream().forEach(t -> testQuestionList.add(t.getTestQuestionId()));

		{
			List<Integer> newList = new ArrayList<>();
			for (int i = 0; i <= testQuestionList.size(); i++) {

				int randomIndex = rand.nextInt(testQuestionList.size());

				newList.add(testQuestionList.get(randomIndex));

				testQuestionList.remove(randomIndex);
			}
			newList.add(testQuestionList.get(0));
			System.out.println(newList);
			return newList;
		}

	}

}
