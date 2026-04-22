package com.Ecommerce.dta;

import java.util.Base64;

import com.Ecommerce.entity.User;

public class UserProfileDTO {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private String role;
    private String profileImage;

    public static UserProfileDTO from(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        if (user.getProfileImageData() != null && user.getProfileImageType() != null) {
            dto.setProfileImage("data:" + user.getProfileImageType() + ";base64,"
                    + Base64.getEncoder().encodeToString(user.getProfileImageData()));
        }
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
