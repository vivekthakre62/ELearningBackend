package com.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	List<Question> findAllByTestId(Long testId);
	
	void deleteById(Long questionId); 
}
