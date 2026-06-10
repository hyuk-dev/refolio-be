package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.mvc.service.S3Service;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        String fileName = s3Service.upload(file);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> getPresignedUrl(@PathVariable String key) {
        String presignedUrl = s3Service.getPresignedUrl(key);
        return ResponseEntity.ok(presignedUrl);
    }
}
