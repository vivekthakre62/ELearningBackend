package com.Ecommerce.configuration;

import java.util.Base64;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.CourseRepo;
import com.Ecommerce.repository.UserRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final String RAJENDRA_EMAIL = "rajendra.thakre@elearning.com";
    private static final String SAMPLE_PNG_BASE64 =
            "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAusB9Wn1W4QAAAAASUVORK5CYII=";

    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepo userRepo, CourseRepo courseRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User teacher = userRepo.findByEmailIgnoreCase(RAJENDRA_EMAIL)
                .orElseGet(this::createRajendraTeacher);

        List<Courses> existingCourses = courseRepo.findByTeacherId(teacher.getId());
        if (existingCourses.size() >= 30) {
            return;
        }

        byte[] imageBytes = Base64.getDecoder().decode(SAMPLE_PNG_BASE64);
        for (int i = existingCourses.size(); i < 30; i++) {
            courseRepo.save(buildCourse(teacher, i + 1, imageBytes));
        }
    }

    private User createRajendraTeacher() {
        User user = new User();
        user.setName("Rajendra Thakre");
        user.setEmail(RAJENDRA_EMAIL);
        user.setPhone("9876543210");
        user.setRole("TEACHER");
        user.setPassword(passwordEncoder.encode("Rajendra@123"));
        user.setProfileImageName("rajendra-profile.png");
        user.setProfileImageType("image/png");
        user.setProfileImageData(Base64.getDecoder().decode(SAMPLE_PNG_BASE64));
        return userRepo.save(user);
    }

    private Courses buildCourse(User teacher, int index, byte[] imageBytes) {
        String[] categories = { "Java", "Spring Boot", "React", "Python", "Data Science", "UI/UX" };
        int[] durations = { 6, 8, 10, 12, 14, 16 };
        double[] prices = { 999, 1299, 1499, 1799, 1999, 2499 };

        Courses course = new Courses();
        course.setTeacher(teacher);
        course.setTitle("Rajendra Masterclass " + index);
        course.setDescription("Practical course " + index
                + " by Rajendra Thakre covering projects, interviews, and industry-ready concepts.");
        course.setCategory(categories[(index - 1) % categories.length]);
        course.setInstructor("Rajendra Thakre");
        course.setDuration(durations[(index - 1) % durations.length]);
        course.setPrice(prices[(index - 1) % prices.length]);
        course.setFileName("rajendra-course-" + index + ".png");
        course.setFileType("image/png");
        course.setData(imageBytes);
        return course;
    }
}
