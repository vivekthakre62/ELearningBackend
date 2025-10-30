package com.Ecommerce.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="questions")
public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private  Long id;

	    private String questionText;

	    private String optionA;
	    private String optionB;
	    private String optionC;
	    private String optionD;

	    private String correctAnswer;

	    @ManyToOne
	    @JoinColumn(name = "test_id")
	    @JsonBackReference
	    private Test test;

	    public Question() {
	    	
	    }
	    public Question(Long id, String questionText, String optionA , String optionB, String optionC, String optionD,String correctAnswer) {
	    	this.id = id;
	    	this.questionText = questionText;
	    	this.optionA = optionA;
	    	this.optionB = optionB;
	    	this.optionC = optionC;
	    	this.optionD = optionD;
	    	this.correctAnswer = correctAnswer;
	    }
		public String getQuestionText() {
			return questionText;
		}

		public void setQuestionText(String questionText) {
			this.questionText = questionText;
		}

		public String getOptionA() {
			return optionA;
		}

		public void setOptionA(String optionA) {
			this.optionA = optionA;
		}

		public String getOptionB() {
			return optionB;
		}

		public void setOptionB(String optionB) {
			this.optionB = optionB;
		}

		public String getOptionC() {
			return optionC;
		}

		public void setOptionC(String optionC) {
			this.optionC = optionC;
		}

		public String getOptionD() {
			return optionD;
		}

		public void setOptionD(String optionD) {
			this.optionD = optionD;
		}

		public String getCorrectAnswer() {
			return correctAnswer;
		}

		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}

		public Test getTest() {
			return test;
		}

		public void setTest(Test test) {
			this.test = test;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
}
