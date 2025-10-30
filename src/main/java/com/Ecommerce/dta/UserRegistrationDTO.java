package com.Ecommerce.dta;


import java.io.Serializable;
import java.time.LocalDateTime;
import com.Ecommerce.entity.RegisterCourses;

public class UserRegistrationDTO implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	private Long courseId;
    private String courseTitle;
    private String courseCategory;
    private String courseInstructor;
    private double coursePrice;
    private LocalDateTime registrationDate;

    public UserRegistrationDTO(RegisterCourses reg) {
        this.courseId = reg.getCourse().getId();
        this.courseTitle = reg.getCourse().getTitle();
        this.courseCategory = reg.getCourse().getCategory();
        this.courseInstructor = reg.getCourse().getInstructor();
        this.coursePrice = reg.getCourse().getPrice();
        // Optional: if you have registration date in your entity
        // this.registrationDate = reg.getRegistrationDate();
    }

    // Getters
    public Long getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseCategory() { return courseCategory; }
    public String getCourseInstructor() { return courseInstructor; }
    public double getCoursePrice() { return coursePrice; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
}
