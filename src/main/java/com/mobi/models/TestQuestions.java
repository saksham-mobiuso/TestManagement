package com.mobi.models;

import javax.persistence.CascadeType;
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
	private Integer id;
	private Integer testId;

	private Integer questionId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "testId",referencedColumnName = "id",insertable = false,updatable = false)
	@JsonIgnore
	private TestInfo testInfo;	

	public TestQuestions() {

	}

	public TestQuestions(Integer id, Integer testsId, Integer questionId, TestInfo testInfo) {
		super();
		this.id = id;
		this.testId = testsId;
		this.questionId = questionId;
		this.testInfo = testInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTestsId() {
		return testId;
	}

	public void setTestsId(Integer testsId) {
		this.testId = testsId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public TestInfo getTestInfo() {
		return testInfo;
	}

	public void setTestInfo(TestInfo testInfo) {
		this.testInfo = testInfo;
	}
	

}
