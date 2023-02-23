package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobi.models.Tests;

@Service
public interface TestRepository extends CrudRepository<Tests, Integer>{
	
	List<Tests> findByTestId(Integer testId);

}
