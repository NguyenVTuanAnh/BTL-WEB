package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.EmailService;
import food_store.example.foodstore.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private MailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;




    @Override
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("nguyentuananhaz9@gmail.com");
        message.setSubject("hellkooooo");
        message.setText("heloo tao la tuấn");
        mailSender.send(message);
    }


    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, isHtml);
            this.javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            System.out.println("ERROR SEND EMAIL: " + e);
        }
    }

    @Async
    @Override
    public void sendEmailTemplate(String to, String subject, String templateName) {
        Context context = new Context();
        User user = userService.getUserByEmailAndProvider(subject, ProviderEnum.LOCAL);
        context.setVariable("code", user.getCode());
        String content = templateEngine.process(templateName, context);
        sendEmailSync(to, subject, content, false, true);
    }





}
