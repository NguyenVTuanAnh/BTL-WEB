package food_store.example.foodstore.controller;

import food_store.example.foodstore.dto.UserLogin;
import food_store.example.foodstore.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }



    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "login";
    }


    @PostMapping("/login")
    public String register(@ModelAttribute("userLogin") @Valid UserLogin userLogin) {
        System.out.println(userLogin.getEmail());
        return "index";
    }



}
