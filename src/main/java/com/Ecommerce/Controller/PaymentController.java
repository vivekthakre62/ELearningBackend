package com.Ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.entity.PaymentCourses;
import com.Ecommerce.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
  @PostMapping("/add/{userId}/{courseId}")
  public ResponseEntity<PaymentCourses> addPayment(@PathVariable Long userId,
		  @PathVariable Long courseId){
	     PaymentCourses payment = paymentService.addPayment(userId, courseId);
	     return ResponseEntity.ok(payment);
  }
	
}
