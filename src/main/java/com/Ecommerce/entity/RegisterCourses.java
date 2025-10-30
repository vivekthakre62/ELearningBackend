package com.Ecommerce.entity;

import jakarta.persistence.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name="registerCourses", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}))
public class RegisterCourses implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-registrations")

    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference("course-registrations")
    private Courses course;


    public RegisterCourses() {}

    public RegisterCourses(Long id, User user, Courses course) {
        this.id = id;
        this.user = user;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Courses getCourse() { return course; }
    public void setCourse(Courses course) { this.course = course; }

   }
