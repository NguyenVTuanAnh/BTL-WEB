package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.model.User;
import food_store.example.foodstore.security.CustomUserDetail;
import food_store.example.foodstore.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public void updateUserDetails(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();

        // Tạo một đối tượng mới với ảnh đại diện mới
        CustomUserDetail updatedUserDetails = new CustomUserDetail(
                user,
                userDetails.getAuthorities()
        );

        // Cập nhật SecurityContext với thông tin mới
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                updatedUserDetails, authentication.getCredentials(), authentication.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
