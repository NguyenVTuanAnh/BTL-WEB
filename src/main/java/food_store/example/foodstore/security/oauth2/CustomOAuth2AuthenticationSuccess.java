package food_store.example.foodstore.security.oauth2;

import food_store.example.foodstore.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();

        String email = null;
        String provider = null;

        if (principal instanceof CustomOAuth2User customOAuth2User) {
            email = ((CustomOAuth2User) principal).getEmail();
            provider = customOAuth2User.getProvider();
        } else if (principal instanceof DefaultOidcUser) {
            email = ((DefaultOidcUser) principal).getAttribute("email");
        }
        if (email != null) {
            userService.processOAuth2login(email, provider);
        }
        response.sendRedirect("/home");
    }
}
