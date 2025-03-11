package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.UploadService;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadServiceImpl implements UploadService {


    @Autowired
    private UserService userService;

    public static String UPLOAD_DIRECTORY = "src/main/resources/static/uploads/";


    @Override
    public void uploadImage(MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails customUserDetails) {
            User user = userService.findByEmail(customUserDetails.getUsername());
            String urlImage = "/uploads/" + fileNames;
            user.setUrlImage(urlImage);
            userService.updateUser(user);
        }

    }


}
