package com.Ecommerce.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String phone;
    private String password;
    private String role;
    private String profileImageName;
    private String profileImageType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImageData;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
   @JsonIgnore
    private List<Courses> createCourses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     @JsonIgnore
    private List<RegisterCourses> registrations;
     
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Test> tests;
    
    public User() {}

    public User(Long id, String email, String name, String phone, String password, String role,
                List<RegisterCourses> registrations, List<Courses> createCourses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.registrations = registrations;
        this.createCourses = createCourses;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getProfileImageName() { return profileImageName; }
    public void setProfileImageName(String profileImageName) { this.profileImageName = profileImageName; }

    public String getProfileImageType() { return profileImageType; }
    public void setProfileImageType(String profileImageType) { this.profileImageType = profileImageType; }

    public byte[] getProfileImageData() { return profileImageData; }
    public void setProfileImageData(byte[] profileImageData) { this.profileImageData = profileImageData; }

    public List<RegisterCourses> getRegistrations() { return registrations; }
    public void setRegistrations(List<RegisterCourses> registrations) { this.registrations = registrations; }

    public List<Courses> getCreateCourses() { return createCourses; }
    public void setCreateCourses(List<Courses> createCourses) { this.createCourses = createCourses; }

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
}
