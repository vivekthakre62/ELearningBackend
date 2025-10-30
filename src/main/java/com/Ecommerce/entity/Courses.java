package com.Ecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "courses")
public class Courses implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private String fileName;
    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
     
    
//    private LocalDateTime uploadDate = LocalDateTime.now();

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @JsonBackReference("teacher-courses")
    private User teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("course-registrations")
    private List<RegisterCourses> registerations;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contents> contents;
    
    

    public Courses() {}

    public Courses(Long id, String title, String description, String category, String instructor, int duration,
                   double price, String fileName, String fileType, byte[] data, LocalDateTime uploadDate,
                   List<RegisterCourses> registerations, User teacher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.instructor = instructor;
        this.duration = duration;
        this.price = price;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
//        this.uploadDate = uploadDate;
        this.registerations = registerations;
        this.teacher = teacher;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public User getTeacher() { return teacher; }
    public void setTeacher(User teacher) { this.teacher = teacher; }

    public List<RegisterCourses> getRegisterations() { return registerations; }
    public void setRegisterations(List<RegisterCourses> registerations) { this.registerations = registerations; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Contents> getContents() {
		return contents;
	}

	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}

//    public LocalDateTime getUploadDate() { return uploadDate; }
//    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
}
