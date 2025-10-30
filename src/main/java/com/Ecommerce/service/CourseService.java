package com.Ecommerce.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.CourseRepo;
import com.Ecommerce.repository.UserRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    /**
     * Add a new course and clear cache for "all courses" since data has changed.
     */
    @CacheEvict(value = { "ALL_COURSES", "COURSE_BY_ID" }, allEntries = true)
    public Courses addCourse(Courses course, MultipartFile file, Long teacherId) throws IOException {
        User teacher = userRepo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        course.setTeacher(teacher);

        if (file != null && !file.isEmpty()) {
            course.setFileName(file.getOriginalFilename());
            course.setFileType(file.getContentType());
            course.setData(file.getBytes());
        }

        return courseRepo.save(course);
    }

    /**
     * Delete a course and clear caches to keep data in sync.
     */
    @CacheEvict(value = { "ALL_COURSES", "COURSE_BY_ID" }, allEntries = true)
    public void deleteCourse(Long courseId) {
        courseRepo.deleteById(courseId);
    }

    /**
     * Fetch all courses – cache the entire list.
     */
    @Cacheable(value = "ALL_COURSES")
    public List<Map<String, Object>> getAllCourses() {
        List<Courses> courses = courseRepo.findAll();
        return courses.stream().map(course -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", course.getId());
            map.put("title", course.getTitle());
            map.put("description", course.getDescription());
            map.put("category", course.getCategory());
            map.put("instructor", course.getInstructor());
            map.put("duration", course.getDuration());
            map.put("price", course.getPrice());
            if (course.getData() != null && course.getFileType() != null) {
                map.put("image", "data:" + course.getFileType() + ";base64," +
                        Base64.getEncoder().encodeToString(course.getData()));
            }
            return map;
        }).toList();
    }

    /**
     * Get a single course by ID – cache individual courses separately.
     */
    @Cacheable(value = "COURSE_BY_ID", key = "#id")
    public Courses getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
    }

    /**
     * Update a course and update its cache entry immediately.
     */
    @CachePut(value = "COURSE_BY_ID", key = "#courseId")
    @CacheEvict(value = "ALL_COURSES", allEntries = true)
    public Courses updateCourse(Long courseId, Courses updatedCourseData) {
        Courses existingCourse = getCourseById(courseId);

        existingCourse.setTitle(updatedCourseData.getTitle());
        existingCourse.setCategory(updatedCourseData.getCategory());
        existingCourse.setDescription(updatedCourseData.getDescription());
        existingCourse.setInstructor(updatedCourseData.getInstructor());
        existingCourse.setDuration(updatedCourseData.getDuration());
        existingCourse.setPrice(updatedCourseData.getPrice());

        return courseRepo.save(existingCourse);
    }
}
