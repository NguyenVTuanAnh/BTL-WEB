package food_store.example.foodstore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    void uploadImage(MultipartFile file) throws IOException;
}
