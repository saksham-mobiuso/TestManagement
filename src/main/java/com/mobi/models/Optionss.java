package com.mobi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = {"correctAnswer"},allowSetters = true)
public class Optionss {
	

	@Column(name = "question_id")
	private Integer questionId;                              
	@Id                                                     //BUG : Can't add duplicate options from POST API														
	@Column(name = "option_value")							//FIX : Add new field serial number and make it @Id. 
	private String optionValue;       
	
	private boolean correctAnswer;
	
	@ManyToOne
	@JoinColumn(name = "question_id",referencedColumnName = "question_id",insertable = false,updatable = false)
	@JsonIgnore
	private Questions questions;
		
	public Optionss() {
		
	}
	
	public Optionss(Integer questionId, String optionValue, boolean correctAnswer,
			Questions questions) {
		super();
		this.questionId = questionId;
		this.optionValue = optionValue;
		this.correctAnswer = correctAnswer;
		this.questions = questions;
	}


	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
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
