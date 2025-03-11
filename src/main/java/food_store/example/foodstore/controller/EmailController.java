package food_store.example.foodstore.controller;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.service.EmailService;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("/email")
    //@Scheduled(cron = "*/10 * * * * *")
    public String sendSimpleEmail() {
        return "Hello World";
    }


    @GetMapping("/password-forgot")
    public String forgotPassword(Model model) {
        return "password-forgot";
    }




    @GetMapping("/change-password")
    public String changePassword(Model model) {
        return "change-password";
    }


    @PostMapping("/change-password")
    public String changePasswordConfirm(Model model
            , @ModelAttribute("npassword") String newPassword
            , @ModelAttribute("email") String email
            , @ModelAttribute("code") String code
    ) {
        if (userService.findByCodeAndProvider(code, ProviderEnum.valueOf("LOCAL")) != null){
            userService.updatePassword(
                    email, newPassword, ProviderEnum.valueOf("LOCAL")
            );
            return "redirect: login";
        }
        return "change-password";

    }



    @PostMapping("/email")
    public String sendEmail(@RequestParam("email") String email, Model model) {
        //emailService.sendSimpleEmail();
        emailService.sendEmailTemplate(email, "nguyentuananhaz9@gmail.com", "email-template");
        return "change-password";
    }







}
