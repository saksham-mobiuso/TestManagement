package com.mobi.models;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value = {"userAnswer"},allowSetters = true)
@Entity
@Table(name = "questions")
public class Questions {
	
	@Id
	@Column(name = "q_id")
	private Integer questionId;
	@Column(name = "q_text")
	private String question;
	@OneToMany(mappedBy = "questionId",cascade = CascadeType.ALL)
	private List<Optionss> Optionss;
	
	@Column(name = "q_type")
	private String questionType;
	
	@Column(name = "q_category")
	private String questionCategory;
	
	@Transient
	private String userAnswer;

	public Questions() {

	}

	public Questions(Integer questionId, String question, List<Optionss> optionss, String questionType,
			String questionCategory, String userAnswer) {
		super();
		this.questionId = questionId;
		this.question = question;
		Optionss = optionss;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.userAnswer = userAnswer;
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
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
		
}
