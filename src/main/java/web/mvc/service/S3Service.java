package web.mvc.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    public String upload(MultipartFile file) throws IOException;

    public String getPresignedUrl(String key);
}
