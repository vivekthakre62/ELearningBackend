package com.Ecommerce.dta;

import java.io.Serializable;
import java.util.List;

public class CourseWithStudentsDTO implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private Long courseId;
    private String title;
    private String description;
    private List<StudentDTO> students;

    public CourseWithStudentsDTO() {}

    public CourseWithStudentsDTO(Long courseId, String title, String description, List<StudentDTO> students) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.students = students;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }
    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
