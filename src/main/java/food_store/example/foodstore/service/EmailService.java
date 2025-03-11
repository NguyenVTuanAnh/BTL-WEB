package food_store.example.foodstore.service;

public interface EmailService {
    void sendSimpleEmail();
    void sendEmailTemplate(String to, String subject, String body);
}
