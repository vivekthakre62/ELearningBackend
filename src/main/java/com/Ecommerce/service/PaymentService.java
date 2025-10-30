package com.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.PaymentCourses;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.CourseRepo;
import com.Ecommerce.repository.PaymentCoursesRepo;
import com.Ecommerce.repository.UserRepo;


@Service
public class PaymentService {

	@Autowired
	private PaymentCoursesRepo paymentRepo;
	 
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	 @CachePut(value = "payments", key = "#userId + '-' + #courseId")
	    public PaymentCourses addPayment(Long userId, Long courseId) {
	        User user = userRepo.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Courses course = courseRepo.findById(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        PaymentCourses payment = new PaymentCourses();
	        payment.setCourse(course);
	        payment.setUser(user);
	        return paymentRepo.save(payment);
	        
	    }
}
