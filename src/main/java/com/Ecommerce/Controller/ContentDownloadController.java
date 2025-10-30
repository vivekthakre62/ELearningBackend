package com.Ecommerce.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
@CrossOrigin("http://localhost:3000")
public class ContentDownloadController {

    private final String UPLOAD_DIR = "uploads"; // folder where files are stored

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        try {
            File file = Paths.get(UPLOAD_DIR).resolve(fileName).toFile();

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            String contentType = "application/octet-stream"; // default
            if (fileName.endsWith(".pdf")) contentType = "application/pdf";
            else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) contentType = "image/jpeg";
            else if (fileName.endsWith(".png")) contentType = "image/png";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(file.length())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error reading file");
        }
    }
}

