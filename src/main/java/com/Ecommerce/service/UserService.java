package com.Ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ecommerce.dta.LoginResponse;
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

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse("registered successfully!", savedUser, token);
    }

    // ✅ Login — will use Redis cache automatically if user already exists in cache
    @Cacheable(value = "USER", key = "#user.email")
    public LoginResponse login(User user) {
        // If user is not cached, Spring will execute this method and then cache the result

        User dbUser = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!pass.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.getRole().equalsIgnoreCase(dbUser.getRole())) {
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
    
}
