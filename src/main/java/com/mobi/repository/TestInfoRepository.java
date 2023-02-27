package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mobi.models.TestInfo;

public interface TestInfoRepository extends CrudRepository<TestInfo, Integer>{
	List<TestInfo> findByTestId(Integer testId);
}
