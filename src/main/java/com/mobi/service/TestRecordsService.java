package com.mobi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mobi.exceptions.CustomError;
import com.mobi.models.TestRecords;
import com.mobi.repository.TestRecordsRepository;

@Service
public class TestRecordsService {
	
	@Autowired
	TestRecordsRepository testRecordsRepository;

	public void save(TestRecords testReport) {
		testRecordsRepository.save(testReport);
	}

	public ResponseEntity<CustomError> testResults(Authentication auth) {
		String currentUser = auth.getName();
		
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(),testRecordsRepository.findByUserName(currentUser));
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<CustomError> allTestResult() {
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(),testRecordsRepository.findAll());
		return ResponseEntity.status(code).body(body);
	}

	public ResponseEntity<CustomError> findByUserName(String username) {
		HttpStatus code = HttpStatus.OK;
		CustomError body = new CustomError(new Date(), code.toString(),testRecordsRepository.findByUserName(username));
		return ResponseEntity.status(code).body(body);
	}
}
