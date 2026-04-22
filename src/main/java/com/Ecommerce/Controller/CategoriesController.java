package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.entity.Categories;
import com.Ecommerce.service.CategoriesService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:3000")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;
	
	@PostMapping("/add")
	public ResponseEntity<Categories> setCategories(@RequestBody Categories categories){
		return ResponseEntity.ok(categoriesService.setCategory(categories));
	}
	@GetMapping("/get")
	public ResponseEntity<List<Categories>> getCategories(){
		return ResponseEntity.ok(categoriesService.getAllCategories());
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable long id){
		return ResponseEntity.ok(categoriesService.deleteCategory(id));
	}
}
