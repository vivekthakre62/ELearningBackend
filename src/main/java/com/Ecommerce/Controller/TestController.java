package com.Ecommerce.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Ecommerce.entity.Test;
import com.Ecommerce.service.TestService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    // ✅ Add New Test (with userId in path)
    @PostMapping("/add/{userId}")
    public ResponseEntity<Test> addTest(@PathVariable Long userId, @RequestBody Test test) {
        return ResponseEntity.ok(testService.addNewTest(userId, test));
    }

    // ✅ Show Tests for specific user
    @GetMapping("/show/{userId}")
    public ResponseEntity<List<Test>> showTests(@PathVariable Long userId) {
        return ResponseEntity.ok(testService.showTests(userId));
    }

  
    @PutMapping("/update/{testId}")
    public ResponseEntity<Test> updateTest(@PathVariable Long testId, @RequestBody Test test) {
        return ResponseEntity.ok(testService.updateTest(testId, test));
    }


    @DeleteMapping("/delete/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok("Test deleted successfully");
    }
    @GetMapping("/showAll")
    public ResponseEntity<List<Test>> showAll(){
    	return ResponseEntity.ok(testService.showAllTest());
    }
}
