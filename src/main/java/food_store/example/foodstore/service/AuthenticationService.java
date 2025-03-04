package food_store.example.foodstore.service;

import food_store.example.foodstore.model.User;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    void updateUserDetails(User user);
}
