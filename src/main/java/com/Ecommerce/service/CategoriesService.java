package com.Ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.Ecommerce.entity.Categories;
import com.Ecommerce.repository.CategoriesRepo;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    // ✅ Cache all categories list
    @Cacheable(value = "categoriesList")
    public List<Categories> getAllCategories() {
        System.out.println("Fetching categories from DB...");
        return categoriesRepo.findAll();
    }

    // ✅ Add new category and update cache
    @CachePut(value = "categories", key = "#result.id")
    @CacheEvict(value = "categoriesList", allEntries = true) // clear cached list when new category added
    public Categories setCategory(Categories category) {
        System.out.println("Saving new category and updating cache...");
        return categoriesRepo.save(category);
    }
    // ✅ Optional: Delete a category and remove from cache
    @CacheEvict(value = { "categories", "categoriesList" }, allEntries = true)
    public void deleteCategory(Long id) {
        System.out.println("Deleting category and clearing cache...");
        categoriesRepo.deleteById(id);
    }
}
