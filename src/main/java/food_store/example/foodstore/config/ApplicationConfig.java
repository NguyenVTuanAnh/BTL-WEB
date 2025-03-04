package food_store.example.foodstore.config;

import food_store.example.foodstore.model.Permission;
import food_store.example.foodstore.model.Role;
import food_store.example.foodstore.model.User;
import food_store.example.foodstore.service.PermissionService;
import food_store.example.foodstore.service.RoleService;
import food_store.example.foodstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            User user = userService.findByEmail("admin@gmail.com");
            if (user == null){

                Permission permission = Permission.builder()
                        .name("ALL")
                        .build();
                permissionService.addPermission(permission);

                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(permission);

                Role adminRole = Role.builder()
                        .name("ADMIN")
                        .permissions(permissionList)
                        .build();
                roleService.addRole(adminRole);

                User admin = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .role(adminRole)
                        .build();

                userService.addUser(admin);
            }
            if (userService.findByEmail("user@gmail.com") == null){
                Permission permission = Permission.builder()
                        .name("READ")
                        .build();
                permissionService.addPermission(permission);

                List<Permission> permissionList = new ArrayList<>();
                permissionList.add(permission);

                Role role = Role.builder()
                        .name("USER")
                        .permissions(permissionList)
                        .build();
                roleService.addRole(role);

                User admin = User.builder()
                        .email("user@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .role(role)
                        .build();

                userService.addUser(admin);
            }
        };
    }
}
