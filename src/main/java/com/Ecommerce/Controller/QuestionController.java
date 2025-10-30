package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.entity.Question;
import com.Ecommerce.service.QuestionService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/add/{testId}")
	public ResponseEntity<Question> addQuestion( 
			@PathVariable Long testId,
			@RequestBody Question question){
		return ResponseEntity.ok(questionService.addQuestion( testId,question));
	}
	@GetMapping("/show/{testId}")
	public ResponseEntity<List<Question>> showQuestions(@PathVariable Long testId){
		return ResponseEntity.ok(questionService.showQuestionByTestId(testId));
	}
	
	@PutMapping("/update/{testId}")
	public ResponseEntity<Question> updateQuestion(@PathVariable Long testId, @RequestBody Question question){
		return ResponseEntity.ok(questionService.updateQuestion(testId, question));
	}
	
	@DeleteMapping("/delete/{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId){
		return ResponseEntity.ok(questionService.deleteQuestion(questionId));
	}
}
