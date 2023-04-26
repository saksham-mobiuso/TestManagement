package com.mobi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobi.models.TestInfo;
import com.mobi.repository.TestInfoRepository;

@Service
public class TestInfoService {
	
	@Autowired
	TestInfoRepository testInfoRepository;

	public Optional<TestInfo> findById(Integer id) {
		return testInfoRepository.findById(id);
	}

	public Optional<TestInfo> findByTestId(Integer testId) {
		return testInfoRepository.findById(testId);
	}

	public void save(TestInfo testInfo) {
		testInfoRepository.save(testInfo);
	}

	public Iterable<TestInfo> findAll() {
		return testInfoRepository.findAll();
	}

	public void deleteByTestId(Integer testId) {
		testInfoRepository.deleteById(testId);
	}
	
	

}
