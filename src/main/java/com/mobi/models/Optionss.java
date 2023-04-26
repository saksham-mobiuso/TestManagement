package com.mobi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(value = {"correctAnswer"},allowSetters = true)
public class Optionss {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	@Column(name = "id")
	private Integer sr_no;
	
	@Column(name = "q_id")
	private Integer questionId;                              
											
	@Column(name = "option_value")
	private String optionValue;       
	
	private boolean correctAnswer;
	
	@ManyToOne
	@JoinColumn(name = "q_id",referencedColumnName = "q_id",insertable = false,updatable = false)
	@JsonIgnore
	private Questions questions;
		
	public Optionss() {
		
	}
	public Optionss(Integer sr_no, Integer questionId, String optionValue, boolean correctAnswer, Questions questions) {
		super();
		this.sr_no = sr_no;
		this.questionId = questionId;
		this.optionValue = optionValue;
		this.correctAnswer = correctAnswer;
		this.questions = questions;
	}
	public Integer getSr_no() {
		return sr_no;
	}
	public void setSr_no(Integer sr_no) {
		this.sr_no = sr_no;
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
