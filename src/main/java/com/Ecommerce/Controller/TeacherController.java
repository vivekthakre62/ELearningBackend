package com.Ecommerce.Controller;

import com.Ecommerce.dta.CourseWithStudentsDTO;
import com.Ecommerce.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/students/{teacherId}")
    public List<CourseWithStudentsDTO> getRegisteredStudents(@PathVariable Long teacherId) {
        return teacherService.getRegisteredStudents(teacherId);
    }
}
