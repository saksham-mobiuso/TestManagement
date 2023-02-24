package com.mobi.models;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
	
	@Id
	@Column(name = "question_id")
	private Integer questionId;
	@Column(name = "question")
	private String question;
	@OneToMany(mappedBy = "questionId",cascade = CascadeType.ALL)
	private List<Optionss> Optionss;
	
	private String questionType;
		
	public Questions() {

	}
	
	public Questions(Integer questionId, String question, List<com.mobi.models.Optionss> optionss, String questionType) {
		super();
		this.questionId = questionId;
		this.question = question;
		Optionss = optionss;
		this.questionType = questionType;

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
	
		
}
