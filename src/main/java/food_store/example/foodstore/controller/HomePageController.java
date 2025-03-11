package food_store.example.foodstore.controller;

import food_store.example.foodstore.dto.UserLogin;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("user", new UserLogin());
        return "index";
    }



//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "email-template";
    }
}
