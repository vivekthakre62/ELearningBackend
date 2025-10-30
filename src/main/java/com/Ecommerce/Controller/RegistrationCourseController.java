package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.dta.UserRegistrationDTO;
import com.Ecommerce.entity.RegisterCourses;
import com.Ecommerce.entity.User;
import com.Ecommerce.service.RegisterCourseService;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("http://localhost:3000")
public class RegistrationCourseController {

	@Autowired
	private RegisterCourseService registerCourseService;
	
	@PostMapping("/registerCourse/{courseId}")
	public ResponseEntity<RegisterCourses>register(@RequestBody User user, @PathVariable Long courseId){
	RegisterCourses  registeration = registerCourseService.registerCourse(user, courseId);
	return ResponseEntity.ok(registeration);
	}
	@GetMapping("/show/{userId}")
	public ResponseEntity<List<UserRegistrationDTO>> showAllCourses(@PathVariable Long userId){
	    List<UserRegistrationDTO>courses = registerCourseService.getUserRegistrations(userId);
	    return ResponseEntity.ok(courses);
	}
}
