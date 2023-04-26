package com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobi.models.TestRecords;

@Repository
public interface TestRecordsRepository extends CrudRepository<TestRecords, Integer>{
	
	List<TestRecords> findByUserName(String userName);

}
