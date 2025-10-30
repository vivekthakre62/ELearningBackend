package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.dta.ContentDTO;
import com.Ecommerce.service.ContentService;

@RestController
@RequestMapping("/api/contents")
@CrossOrigin("http://localhost:3000")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/upload/{courseId}")
    public ResponseEntity<?> uploadFile(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file) {
        return contentService.uploadFile(courseId, file);
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<List<ContentDTO>> getContents(@PathVariable Long courseId) {
        return ResponseEntity.ok(contentService.getContentsByCourse(courseId));
    }
    
    @DeleteMapping("/delete/{courseId}/{contentId}")
    public ResponseEntity<?> deleteContent(
            @PathVariable Long courseId,
            @PathVariable Long contentId) {
        return contentService.deleteContent(contentId, courseId);
    }
}
