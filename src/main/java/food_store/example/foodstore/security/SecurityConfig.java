package food_store.example.foodstore.security;

import food_store.example.foodstore.security.oauth2.CustomOAuth2AuthenticationSuccess;
import food_store.example.foodstore.security.oauth2.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;


    @Autowired
    private CustomOAuth2AuthenticationSuccess customOAuth2AuthenticationSuccess;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login","/logout-success","/oauth2/**").permitAll()
                        .requestMatchers("/home","/products","/email","/waiting-email","/change-password","/password-forgot").permitAll()
                        .requestMatchers( "/css/**", "/js/**", "/images/**","/uploads/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(loginSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .invalidateHttpSession(true)  // ✅ Hủy session khi logout
                        .deleteCookies("JSESSIONID")  // ✅ Xóa cookie JSESSIONID
                        .logoutSuccessUrl("/login-success") // ✅ Chuyển hướng sau khi logout
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // ✅ Chỉ tạo session khi cần
                        .invalidSessionUrl("/login?sessionExpired") // ✅ Chuyển hướng khi session hết hạn
                        .maximumSessions(1) // ✅ Mỗi user chỉ có 1 session
                        .maxSessionsPreventsLogin(false) // ✅ Nếu user đăng nhập mới, session cũ bị xóa
                )
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .userInfoEndpoint(userInfor ->
                                userInfor.userService(new CustomOAuth2UserService())   // đăng ký CustomOAuth2UserService
                        )
                        .successHandler(customOAuth2AuthenticationSuccess)
                        .permitAll()
                )
                ;
        return http.build();
    }




}
