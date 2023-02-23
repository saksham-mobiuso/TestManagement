package com.mobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.repository.AnswerRepository;

@Service
public class AnswersService {
	
	@Autowired
	AnswerRepository answerRepository;


}
