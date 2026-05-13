package com.Ecommerce.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.dta.ContentDTO;
import com.Ecommerce.entity.Contents;
import com.Ecommerce.entity.Courses;
import com.Ecommerce.repository.ContentsRepo;
import com.Ecommerce.repository.CourseRepo;

@Service
public class ContentService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ContentsRepo contentsRepo;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @CacheEvict(value = "contentsByCourse", key = "#courseId")
    public ResponseEntity<?> uploadFile(Long courseId, MultipartFile file) {
        try {
            Courses course = courseRepo.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);
            Files.write(path, file.getBytes());

            Contents content = new Contents(fileName, file.getContentType(), path.toString(), course);
            contentsRepo.save(content);

            return ResponseEntity.ok(Map.of(
                    "message", "File uploaded successfully!",
                    "fileName", fileName,
                    "fileType", file.getContentType()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "File upload failed: " + e.getMessage()));
        }
    }

    public List<ContentDTO> getContentsByCourse(Long courseId) {
        List<Contents> contents = contentsRepo.findAllByCourseId(courseId);
        return contents.stream()
                .map(c -> new ContentDTO(
                        c.getId(),
                        c.getFileName(),
                        c.getFileType(),
                        c.getFilePath(),
                        buildFileUrl(c.getFileName())))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "contentsByCourse", key = "#courseId")
    public ResponseEntity<?> deleteContent(Long contentId, Long courseId) {
        try {
            Contents content = contentsRepo.findById(contentId)
                    .orElseThrow(() -> new RuntimeException("Content not found with ID: " + contentId));

            Path path = Paths.get(content.getFilePath());
            Files.deleteIfExists(path);
            contentsRepo.delete(content);

            return ResponseEntity.ok(Map.of("message", "Content deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to delete content: " + e.getMessage()));
        }
    }

    private String buildFileUrl(String fileName) {
        return baseUrl.replaceAll("/$", "") + "/api/files/download/" + fileName;
    }
}
