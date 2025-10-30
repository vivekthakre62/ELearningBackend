package com.Ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // ✅ important

import com.Ecommerce.entity.Test;
import com.Ecommerce.entity.User;
import com.Ecommerce.repository.TestRepo;
import com.Ecommerce.repository.UserRepo;

@Service
public class TestService {

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private UserRepo userRepo;

    // Add a new test (invalidate teacher’s test cache)
    @Transactional
    @CacheEvict(value = {"testsByTeacher", "allTests"}, allEntries = true)
    public Test addNewTest(Long userId, Test test) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        test.setTeacher(user);
        return testRepo.save(test);
    }

    // Show all tests for a specific teacher (cache it)
    @Transactional(readOnly = true)
    @Cacheable(value = "testsByTeacher", key = "#userId")
    public List<Test> showTests(Long userId) {
        System.out.println("Fetching tests from DB for teacherId: " + userId);
        return testRepo.findAllByTeacherId(userId);
    }

    // Update a specific test and refresh cache
    @Transactional
    @CachePut(value = "test", key = "#testId")
    public Test updateTest(Long testId, Test test) {
        Test testById = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("test not found!"));
        testById.setTitle(test.getTitle());
        testById.setDescription(test.getDescription());
        return testRepo.save(testById);
    }

    // Delete a test and clear related caches
    @Transactional
    @CacheEvict(value = {"test", "testsByTeacher", "allTests"}, allEntries = true)
    public String deleteTest(Long testId) {
        testRepo.deleteById(testId);
        return "Test Deleted Successfully!";
    }

    // Show all tests (cached)
    @Transactional(readOnly = true)
    @Cacheable(value = "allTests")
    public List<Test> showAllTest() {
        System.out.println("Fetching all tests from DB...");
        return testRepo.findAll();
    }
}
