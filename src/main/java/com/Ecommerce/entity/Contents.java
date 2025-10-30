package com.Ecommerce.entity;

import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contents")
public class Contents implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;  // e.g., image/png, application/pdf

    @Column(nullable = false)
    private String filePath;  // store only the path, not the file bytes

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Courses course;

    public Contents() {}

    public Contents(String fileName, String fileType, String filePath, Courses course) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public Courses getCourse() { return course; }
    public void setCourse(Courses course) { this.course = course; }
}
