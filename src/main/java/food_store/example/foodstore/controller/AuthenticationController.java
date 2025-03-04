package food_store.example.foodstore.controller;

import food_store.example.foodstore.dto.UserLogin;
import food_store.example.foodstore.model.Permission;
import food_store.example.foodstore.model.Role;
import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.PermissionService;
import food_store.example.foodstore.service.RoleService;
import food_store.example.foodstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    private static String urlImage = "/uploads/anonymous.jpg";


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userLogin") UserLogin userLogin, Model model) {
        User user = User.builder()
                .email(userLogin.getEmail())
                .password(passwordEncoder.encode(userLogin.getPassword()))
                .urlImage(urlImage)
                .role(roleService.findRoleByName("USER"))
                .build();
        userService.addUser(user);
        model.addAttribute("userLogin", userLogin);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "login";
    }


    @PostMapping("/login")
    public String register(@RequestBody @Valid UserLogin userLogin) {
        System.out.println(userLogin.getEmail());
        return "index";
    }

    @GetMapping("/logout-success")
    public String logout() {
        return "logout";
    }






}
