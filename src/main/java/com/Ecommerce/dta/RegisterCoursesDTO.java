package com.Ecommerce.dta;

import java.io.Serializable;

public class RegisterCoursesDTO implements Serializable {
  
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private Long courseId;
    private String courseTitle;
    private String courseDescription;
    private String courseFileType;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhone;

    public RegisterCoursesDTO() {
    }

    // All-args constructor
    public RegisterCoursesDTO(Long id,
                              Long courseId, String courseTitle, String courseDescription,
                              String courseFileName, String courseFileType,
                              Long userId, String userName, String userEmail, String userPhone) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
//        this.courseFileName = courseFileName;
        this.courseFileType = courseFileType;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }
    
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

//    public String getCourseFileName() {
//        return courseFileName;
//    }
//
//    public void setCourseFileName(String courseFileName) {
//        this.courseFileName = courseFileName;
//    }

    public String getCourseFileType() {
        return courseFileType;
    }

    public void setCourseFileType(String courseFileType) {
        this.courseFileType = courseFileType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
