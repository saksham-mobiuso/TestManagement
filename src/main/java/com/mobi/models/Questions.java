package com.mobi.models;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "questions")
@JsonIgnoreProperties(value = {"answers"},allowSetters= true)
public class Questions {
	
	@Id
	@Column(name = "question_id")
	private Integer questionId;
	@Column(name = "question")
	private String question;
	@OneToMany(mappedBy = "questionId",cascade = CascadeType.ALL)
	private List<Optionss> Optionss;
	
	private String questionType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Answers answers;
	
		
	public Questions() {

	}
	
	public Questions(Integer questionId, String question, List<com.mobi.models.Optionss> optionss, String questionType,
			Answers answers) {
		super();
		this.questionId = questionId;
		this.question = question;
		Optionss = optionss;
		this.questionType = questionType;
		this.answers = answers;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Optionss> getOptionss() {
		return Optionss;
	}

	public void setOptionss(List<Optionss> optionss) {
		Optionss = optionss;
	}
	
	public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}
		
}
