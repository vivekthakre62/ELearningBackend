package com.Ecommerce.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.dta.LoginResponse;
import com.Ecommerce.dta.UserProfileDTO;
import com.Ecommerce.dta.UserProfileUpdateRequest;
import com.Ecommerce.entity.User;
import com.Ecommerce.service.UserService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	@Transactional
	public ResponseEntity<LoginResponse> register(@RequestBody User user){
		return ResponseEntity.ok(userService.registeration(user));
	}
	
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<LoginResponse> login(@RequestBody User user){
		return ResponseEntity.ok(userService.login(user));
	}
	@GetMapping("/getUserById/{id}")
	@Transactional
	public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/profile/{id}")
	@Transactional
	public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserProfile(id));
	}

	@PutMapping("/profile/{id}")
	@Transactional
	public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Long id,
			@ModelAttribute UserProfileUpdateRequest request,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException{
		return ResponseEntity.ok(userService.updateProfile(id, request, file));
	}
	
}
