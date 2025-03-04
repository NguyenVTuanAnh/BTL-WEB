package food_store.example.foodstore.controller;

import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.AuthenticationService;
import food_store.example.foodstore.service.UploadService;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {


    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        User user = userService.findByEmail(userDetails.getUsername());
        uploadService.uploadImage(file);
        authenticationService.updateUserDetails(user);
        return "redirect:/profile";
    }

}
