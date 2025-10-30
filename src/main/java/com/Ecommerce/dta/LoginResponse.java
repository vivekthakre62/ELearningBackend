package com.Ecommerce.dta;

import java.io.Serializable;

import com.Ecommerce.entity.User;

public class LoginResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private User user;
	
	private String message;
	
	private String token;
	
	public LoginResponse(String message, User user, String token) {
        this.message = message;
        this.user = user;
        this.token = token;
    }

    // Getters & Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
