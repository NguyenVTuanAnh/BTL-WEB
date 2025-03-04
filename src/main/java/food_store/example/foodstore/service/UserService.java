package food_store.example.foodstore.service;

import food_store.example.foodstore.model.User;

public interface UserService {
    User findByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    User getCurrentUser();
}
