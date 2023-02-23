package com.mobi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "answers")
public class Answers {
	
	@Id
	@Column(name = "question_id")
	private Integer questionId;

	private String correctAnswer;
	
	@OneToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn(name = "question_id",referencedColumnName = "question_id")
	private Questions questions;
	

	public Answers() {

	}

	public Answers(Integer questionId, String correctAnswer, Questions questions) {
		super();
		this.questionId = questionId;
		this.correctAnswer = correctAnswer;
		this.questions = questions;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}


}
