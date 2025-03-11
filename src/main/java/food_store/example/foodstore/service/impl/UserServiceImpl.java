package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.model.Permission;
import food_store.example.foodstore.model.Role;
import food_store.example.foodstore.model.User;
import food_store.example.foodstore.repository.UserRepository;
import food_store.example.foodstore.security.CustomUserDetail;
import food_store.example.foodstore.security.oauth2.CustomOAuth2User;
import food_store.example.foodstore.service.AuthenticationService;
import food_store.example.foodstore.service.RoleService;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User userDB = userRepository.findByEmail(user.getEmail());
        userDB.setAddress(user.getAddress());
        userDB.setPhone(user.getPhone());
        userDB.setFullname(user.getFullname());
        String tmp = user.getUrlImage();
        if(user.getUrlImage() != null){
            userDB.setUrlImage(user.getUrlImage());
        }

        authenticationService.updateUserDetails(userRepository.save(userDB));
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            CustomUserDetail customUserDetail = (CustomUserDetail) principal;
            return userRepository.findByEmail(customUserDetail.getUsername());
        } else if (principal instanceof DefaultOidcUser oidcUser){
            return userRepository.findByEmail(oidcUser.getEmail());
        } else if (principal instanceof OAuth2User oauth2User) {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) oauth2User;
            String email = customOAuth2User.getEmail();
            User user = userRepository.findByEmailAndProvider(email,
                    ProviderEnum.valueOf(customOAuth2User.getProvider().toUpperCase()));
            return user;
        } else {
            return null;
        }
    }


    @Override
    public void processOAuth2login(String email, String provider) {
        boolean user = userRepository.existsByEmailAndProvider(email, ProviderEnum.valueOf(provider.toUpperCase()));
        if (user == false) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomOAuth2User oAuth2User = (CustomOAuth2User) principal;
            User userLogin = User.builder()
                    .email(email)
                    .urlImage(oAuth2User.getUrlImage())
                    .fullname(oAuth2User.getFullname())
                    .provider(ProviderEnum.valueOf(provider.toUpperCase()))
                    .role(roleService.findRoleByName("USER"))
                    .build();
            userRepository.save(userLogin);
        }
    }

    @Override
    public User getUserByEmailAndProvider(String email, ProviderEnum providerEnum) {
        return userRepository.findByEmailAndProvider(email, providerEnum);
    }

    @Override
    public void updatePassword(String email, String password, ProviderEnum providerEnum) {
        User user = userRepository.findByEmailAndProvider(email, providerEnum);
        if (user!=null){
            user.setPassword(password);
            userRepository.save(user);
        }

    }
    @Override
    public String generateCode() {
        StringBuilder number = new StringBuilder(5);
        SecureRandom RANDOM = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            number.append(RANDOM.nextInt(10)); // Chọn số từ 0 đến 9
        }
        return number.toString();
    }

    @Override
    public User findByCodeAndProvider(String code, ProviderEnum providerEnum) {
        return userRepository.findByCodeAndProvider(code, providerEnum);
    }

}
