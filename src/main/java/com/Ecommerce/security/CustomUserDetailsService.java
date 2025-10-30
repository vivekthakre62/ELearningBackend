package com.Ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ecommerce.entity.User;
import com.Ecommerce.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepo userRepo;
	
	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepo.findByEmail(email)
				.orElseThrow();
		return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
	}
     
}
