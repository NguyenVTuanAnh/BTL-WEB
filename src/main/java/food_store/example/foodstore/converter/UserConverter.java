package food_store.example.foodstore.converter;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.model.User;
import food_store.example.foodstore.repository.RoleRepository;
import food_store.example.foodstore.security.CustomUserDetail;
import food_store.example.foodstore.security.oauth2.CustomOAuth2User;
import food_store.example.foodstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private RoleService roleService;


    public CustomUserDetail toUserDetails(CustomOAuth2User userOAuth2) {
        User user = User.builder()
                .email(userOAuth2.getEmail())
                .fullname(userOAuth2.getFullname())
                .urlImage(userOAuth2.getUrlImage())
                .role(roleService.findRoleByName("USER"))
                .provider(ProviderEnum.valueOf(userOAuth2.getProvider().toUpperCase()))
                .build();
        return new CustomUserDetail(user, userOAuth2.getAuthorities());
    }


}
