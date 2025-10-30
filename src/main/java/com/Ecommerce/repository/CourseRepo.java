package com.Ecommerce.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.entity.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Long>{
        Optional<Courses> findById(Long id);
        
        
}
