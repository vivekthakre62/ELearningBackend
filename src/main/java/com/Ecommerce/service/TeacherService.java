// TeacherService.java
package com.Ecommerce.service;

import com.Ecommerce.dta.CourseWithStudentsDTO;
import com.Ecommerce.dta.StudentDTO;
import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.UserRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final UserRepo userRepository;

    public TeacherService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<CourseWithStudentsDTO> getRegisteredStudents(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        List<Courses> courses = teacher.getCreateCourses();

        return courses.stream().map(course -> {
            List<StudentDTO> students = course.getRegisterations().stream()
                    .map(reg -> new StudentDTO(
                            reg.getUser().getId(),
                            reg.getUser().getName(),
                            reg.getUser().getEmail(),
                            reg.getUser().getPhone()
                    )).collect(Collectors.toList());

            return new CourseWithStudentsDTO(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    students
            );
        }).collect(Collectors.toList());
    }
}
