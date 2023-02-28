package com.mobi.repository;


import org.springframework.data.repository.CrudRepository;

import com.mobi.models.TestInfo;

public interface TestInfoRepository extends CrudRepository<TestInfo, Integer>{
	TestInfo findByTestId(Integer testId);

}
