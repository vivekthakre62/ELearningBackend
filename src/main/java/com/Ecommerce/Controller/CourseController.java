package com.Ecommerce.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.entity.Courses;
import com.Ecommerce.service.CourseService;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/course")
public class CourseController {
	@Autowired 
	private CourseService courseService;
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<Courses> addCourses(@RequestPart("course") Courses courses,
	        @RequestPart("file") MultipartFile file,
	        @PathVariable Long userId) throws IOException{
	
		Courses course = courseService.addCourse(courses,file,userId);
		return ResponseEntity.ok(course);
	
			}

	@GetMapping("/show")
	public ResponseEntity<List<Map<String,Object>>> showCourses(){
		List<Map<String, Object>> courses = courseService.getAllCourses();
		return ResponseEntity.ok(courses);
	}
	
	@DeleteMapping("/delete/{courseId}")
	public void deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
	}
	@PutMapping("/update/{courseId}")
	public ResponseEntity<Courses>updateCourse(@PathVariable Long courseId,
			@RequestBody Courses course){
		Courses courses = courseService.updateCourse(courseId,course);
		return ResponseEntity.ok(courses);
	}
	
	@GetMapping("/get/{courseId}")
	public ResponseEntity<Courses>getById(@PathVariable Long courseId){
		Courses course = courseService.getCourseById(courseId);
		return ResponseEntity.ok(course);
	}
	}
