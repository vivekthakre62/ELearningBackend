package com.Ecommerce.dta;

import java.util.List;

import com.Ecommerce.entity.Question;

public class TestDto {
    private Long id;
    private String title;
    private String description;
    private List<Question> questions;
    
    public TestDto() {
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
