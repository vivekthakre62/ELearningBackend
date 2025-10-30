package com.Ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.entity.Categories;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {

	Optional<Categories> findById(Long id);
}
