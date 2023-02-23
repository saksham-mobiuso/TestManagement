package com.mobi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serialNumber;
	
	private Integer testId;

	private Integer testQuestionId;
		
	public Tests() {
		super();
		
	}
	
	public Tests(Integer testId, Integer testQuestionId) {
		super();
		this.testId = testId;
		this.testQuestionId = testQuestionId;
	}
	
	public Integer getTestId() {
		return testId;
	}
	
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public Integer getTestQuestionId() {
		return testQuestionId;
	}
	
	public void setTestQuestionId(Integer testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

}
