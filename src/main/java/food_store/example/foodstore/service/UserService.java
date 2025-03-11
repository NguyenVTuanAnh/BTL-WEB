package food_store.example.foodstore.service;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.model.User;

public interface UserService {
    User findByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    User getCurrentUser();
    void processOAuth2login(String email,String provider);
    User getUserByEmailAndProvider(String email, ProviderEnum providerEnum);
    void updatePassword(String email, String password, ProviderEnum providerEnum);
    String generateCode();
    User findByCodeAndProvider(String code, ProviderEnum providerEnum);
}
