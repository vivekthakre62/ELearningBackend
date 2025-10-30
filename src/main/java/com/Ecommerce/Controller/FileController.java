package com.Ecommerce.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("http://localhost:3000")
public class FileController {

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {

        File file = new File("uploads/" + fileName); // use the folder where you store files
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // Determine MediaType
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (fileName.endsWith(".pdf")) mediaType = MediaType.APPLICATION_PDF;
        else if (fileName.endsWith(".png")) mediaType = MediaType.IMAGE_PNG;
        else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) mediaType = MediaType.IMAGE_JPEG;
        else if (fileName.endsWith(".mp4")) mediaType = MediaType.valueOf("video/mp4");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(mediaType)
                .body(resource);
    }
}
