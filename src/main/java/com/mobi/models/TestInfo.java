package com.mobi.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class TestInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String desciption;
	private boolean publishStatus;
	
	@OneToMany(mappedBy = "testId",cascade = CascadeType.ALL)
	private List<TestQuestions> questions;
	
	public TestInfo() {
		super();
	}

	public TestInfo(Integer id, String desciption, boolean publishStatus, List<TestQuestions> questions) {
		super();
		this.id = id;
		this.desciption = desciption;
		this.publishStatus = publishStatus;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public boolean isPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(boolean publishStatus) {
		this.publishStatus = publishStatus;
	}

	public List<TestQuestions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TestQuestions> questions) {
		this.questions = questions;
	}

}
