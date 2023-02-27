package com.mobi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TestQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sr_no;
	private Integer testId;
	
	private Integer testQuestionsId;
	
	@ManyToOne
	@JoinColumn(name = "testId",referencedColumnName = "testId",insertable = false,updatable = false)
	@JsonIgnore
	private TestInfo testInfo;
	
	public TestQuestions() {
		super();
	}

	public TestQuestions(Integer testId, Integer testQuestionsId, TestInfo testInfo) {
		super();
		this.testId = testId;
		this.testQuestionsId = testQuestionsId;
		this.testInfo = testInfo;
	}
	

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getTestQuestionsId() {
		return testQuestionsId;
	}

	public void setTestQuestionsId(Integer testQuestionsId) {
		this.testQuestionsId = testQuestionsId;
	}

	public TestInfo getTestInfo() {
		return testInfo;
	}

	public void setTestInfo(TestInfo testInfo) {
		this.testInfo = testInfo;
	}
	
	
	


	
}
