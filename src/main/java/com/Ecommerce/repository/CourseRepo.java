package com.Ecommerce.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Ecommerce.entity.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Long>{
        Optional<Courses> findById(Long id);
        List<Courses> findByCategoryIgnoreCase(String category);
        List<Courses> findByTeacherId(Long teacherId);

        @Query("""
                SELECT DISTINCT c FROM Courses c
                LEFT JOIN c.teacher t
                WHERE (:category IS NULL OR TRIM(:category) = '' OR LOWER(c.category) = LOWER(TRIM(:category)))
                AND (
                    :keyword IS NULL OR TRIM(:keyword) = ''
                    OR LOWER(c.title) LIKE LOWER(CONCAT('%', TRIM(:keyword), '%'))
                    OR LOWER(c.category) LIKE LOWER(CONCAT('%', TRIM(:keyword), '%'))
                    OR LOWER(c.instructor) LIKE LOWER(CONCAT('%', TRIM(:keyword), '%'))
                    OR LOWER(t.name) LIKE LOWER(CONCAT('%', TRIM(:keyword), '%'))
                )
                """)
        List<Courses> searchCourses(@Param("keyword") String keyword, @Param("category") String category);
}
