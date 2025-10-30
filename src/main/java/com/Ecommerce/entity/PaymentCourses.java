package com.Ecommerce.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="payment")
public class PaymentCourses implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  @OneToOne
	private Courses course;
  
  @OneToOne
  private User user;
	
    private String method;
    
    private String detail;
	public PaymentCourses() {
		
	}
	public PaymentCourses(Long id,Courses course,User user,String method,String detail) {
		this.id = id;
		this.course = course;
		this.method = method;
		this.user = user;
		this.detail = detail;
	}
	

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
