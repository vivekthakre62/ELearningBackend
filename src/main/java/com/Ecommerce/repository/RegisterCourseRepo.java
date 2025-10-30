package com.Ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Ecommerce.entity.Courses;
import com.Ecommerce.entity.RegisterCourses;
import com.Ecommerce.entity.User;

import io.lettuce.core.dynamic.annotation.Param;


@Repository
public interface RegisterCourseRepo extends JpaRepository<RegisterCourses, Long> {

	Optional<RegisterCourses> findById(Long id);
	
	 List<RegisterCourses> findByUserId(Long userId);
	
	boolean existsByUserAndCourse(User user, Courses course);
	
	@Query("SELECT r FROM RegisterCourses r WHERE r.course.teacher.id = :teacherId")
	List<RegisterCourses> findByTeacherId(@Param("teacherId") Long teacherId);
    
	
}
