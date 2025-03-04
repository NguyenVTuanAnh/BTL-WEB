package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.model.User;
import food_store.example.foodstore.repository.UserRepository;
import food_store.example.foodstore.security.CustomUserDetail;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



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
        userRepository.save(userDB);
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            CustomUserDetail customUserDetail = (CustomUserDetail) principal;
            return userRepository.findByEmail(customUserDetail.getUsername());
        } else {
            return null;
        }
    }


}
