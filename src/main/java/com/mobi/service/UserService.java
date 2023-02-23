package com.mobi.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	TestService service;
	
	@Autowired
	QuestionsService questionsService;
	
	
	HashMap<Integer, String> userAnswers = new HashMap<>();   // put user's answer in this map (From front-end)
	

//	public ArrayList<Questions> takeTest(){
//		
//		userAnswers.put(1, "bhavik");
//		userAnswers.put(4,"Bass kr bhai");
//		
//		ArrayList<Questions> testData= service.populateTests();
//		for(Questions test: testData) {
//			System.out.println("Question ID : " + test.getQuestionId());	
//			System.out.println("Question : " + test.getQuestion());	
//			test.getOptionss().forEach(t -> System.out.println("Option : " + t.getOptionValue()));
//		}
//		
//		Questions question = questionsService.getQuestionById(1);
//		int count=0;
//		
//		if(userAnswers.get(1).equals(question.getAnswers())) {
//			System.out.println(userAnswers.get(1) + question.getAnswers());
//			System.out.println(count+=1);
//		}else{
//			System.out.println(count);
//		}
//		
//		return testData;
//	}
	
}
