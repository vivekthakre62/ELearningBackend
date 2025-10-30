package com.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.entity.Test;

public interface TestRepo extends JpaRepository<Test, Long> {

	List<Test> findAllByTeacherId(Long userId);
	
	void deleteById(Long testId);
}
