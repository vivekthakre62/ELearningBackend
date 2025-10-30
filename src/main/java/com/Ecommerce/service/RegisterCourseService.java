package com.Ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ecommerce.dta.UserRegistrationDTO;
import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.RegisterCourses;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.RegisterCourseRepo;

@Service
public class RegisterCourseService {

    @Autowired
    private RegisterCourseRepo registerCourseRepo;

    @Autowired
    private CourseService courseService;

    // ✅ When a user registers for a course, clear their cached registration list
    @CacheEvict(value = "USER_REGISTRATIONS", key = "#user.id")
    public RegisterCourses registerCourse(User user, Long courseId) {
        Courses course = courseService.getCourseById(courseId);
        RegisterCourses regCourse = new RegisterCourses();

        if (registerCourseRepo.existsByUserAndCourse(user, course)) {
            throw new RuntimeException("User is already registered for this course!");
        }

        regCourse.setCourse(course);
        regCourse.setUser(user);
        return registerCourseRepo.save(regCourse);
    }

    // ✅ Cached per user — fetches from DB only once unless cache expires or evicted
    @Transactional(readOnly = true)
    @Cacheable(value = "USER_REGISTRATIONS", key = "#userId")
    public List<UserRegistrationDTO> getUserRegistrations(Long userId) {
        List<RegisterCourses> registrations = registerCourseRepo.findByUserId(userId);
        return registrations.stream()
                .map(UserRegistrationDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Optional — if you want to clear a user's registration cache manually
    @CacheEvict(value = "USER_REGISTRATIONS", key = "#userId")
    public void clearUserRegistrationsCache(Long userId) {
        System.out.println("Cleared cached registration data for user: " + userId);
    }
}
