package com.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.entity.Contents;

public interface ContentsRepo extends JpaRepository<Contents, Long> {
	List<Contents> findAllByCourseId(Long courseId);
	
    void deleteByCourseId(Long courseId);
}
