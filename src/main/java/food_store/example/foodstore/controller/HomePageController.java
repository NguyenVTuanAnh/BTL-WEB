package food_store.example.foodstore.controller;

import ch.qos.logback.core.model.Model;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {


        return "index";
    }



//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}
