package food_store.example.foodstore.controller;

import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.SecureRandom;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getUser(Model model){

        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "profile";

    }


    @PostMapping("/user")
    public String updateUser(@ModelAttribute("user") User user, Model model){
        userService.updateUser(user);
        return "redirect:profile";
    }



}
