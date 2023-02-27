package com.mobi.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TestInfo {
	
	@Id
	private Integer testId;
	private String testDesciption;
	private boolean publishStatus;
	
	@OneToMany(mappedBy = "testId",cascade = CascadeType.ALL)
	private List<TestQuestions> testQuestions;
	
	public TestInfo() {
		super();
	}

	public TestInfo(Integer testId, String testDesciption, boolean publishStatus, List<TestQuestions> testQuestions) {
		super();
		this.testId = testId;
		this.testDesciption = testDesciption;
		this.publishStatus = publishStatus;
		this.testQuestions = testQuestions;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestDesciption() {
		return testDesciption;
	}

	public void setTestDesciption(String testDesciption) {
		this.testDesciption = testDesciption;
	}

	public boolean isPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(boolean publishStatus) {
		this.publishStatus = publishStatus;
	}

	public List<TestQuestions> getTestQuestions() {
		return testQuestions;
	}

	public void setTestQuestions(List<TestQuestions> testQuestions) {
		this.testQuestions = testQuestions;
	}
	

	

}
