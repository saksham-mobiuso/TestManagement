package com.mobi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Optionss {
	

	@Column(name = "question_id")
	private Integer questionId;
	@Id
	@Column(name = "option_value")
	private String optionValue;
	
	@ManyToOne
	@JoinColumn(name = "question_id",referencedColumnName = "question_id",insertable = false,updatable = false)
	@JsonIgnore
	private Questions questions;
		
	public Optionss() {
		
	}
		
	public Optionss(Integer questionId, String optionValue, Questions questions) {
		super();
		this.questionId = questionId;
		this.optionValue = optionValue;
		this.questions = questions;
	}

	public Integer getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	public String getOptionValue() {
		return optionValue;
	}
	
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	

}
