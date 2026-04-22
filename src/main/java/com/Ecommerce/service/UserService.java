package com.Ecommerce.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.dta.LoginResponse;
import com.Ecommerce.dta.UserProfileDTO;
import com.Ecommerce.dta.UserProfileUpdateRequest;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.UserRepo;
import com.Ecommerce.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder pass;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register new user and update Redis cache
    @CachePut(value = "loginCache", key = "#user.email")
    public LoginResponse registeration(User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return new LoginResponse("Password cannot be empty", null, null);
        }

        user.setPassword(pass.encode(user.getPassword()));
        User savedUser = userRepo.save(user);

        String token = jwtUtil.generateToken(savedUser.getEmail());

        return new LoginResponse("registered successfully!", savedUser, token);
    }

    // ✅ Login — will use Redis cache automatically if user already exists in cache
    @Cacheable(value = "USER", key = "#user.email + '_' + #user.role")
    public LoginResponse login(User user) {
        // If user is not cached, Spring will execute this method and then cache the result

        User dbUser = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!pass.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (user.getRole() == null || !user.getRole().equalsIgnoreCase(dbUser.getRole())) {
            throw new RuntimeException("Role doesn't match!");
        }

        String token = jwtUtil.generateToken(dbUser.getEmail());
        return new LoginResponse(dbUser.getRole(), dbUser, token);
    }
    
    @Cacheable(value = "USERS", key = "#id")
    public Optional<User> getUserById(Long id){
    	Optional<User>user = userRepo.findById(id);
    	return user;
    }

    public UserProfileDTO getUserProfile(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return UserProfileDTO.from(user);
    }

    public UserProfileDTO updateProfile(Long id, UserProfileUpdateRequest request, MultipartFile file) throws IOException {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            String normalizedEmail = request.getEmail().trim();
            if (userRepo.existsByEmailIgnoreCaseAndIdNot(normalizedEmail, id)) {
                throw new RuntimeException("Email already in use");
            }
            user.setEmail(normalizedEmail);
        }

        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName().trim());
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            user.setPhone(request.getPhone().trim());
        }
        if (request.getRole() != null && !request.getRole().isBlank()) {
            user.setRole(request.getRole().trim());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(pass.encode(request.getPassword().trim()));
        }
        if (file != null && !file.isEmpty()) {
            user.setProfileImageName(file.getOriginalFilename());
            user.setProfileImageType(file.getContentType());
            user.setProfileImageData(file.getBytes());
        }

        return UserProfileDTO.from(userRepo.save(user));
    }
    
}
