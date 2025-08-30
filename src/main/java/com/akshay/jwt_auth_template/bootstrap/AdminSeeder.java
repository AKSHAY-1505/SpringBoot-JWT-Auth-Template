package com.akshay.jwt_auth_template.bootstrap;

import com.akshay.jwt_auth_template.entities.Role;
import com.akshay.jwt_auth_template.entities.RoleEnum;
import com.akshay.jwt_auth_template.entities.User;
import com.akshay.jwt_auth_template.repositories.RoleRepository;
import com.akshay.jwt_auth_template.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2) // Runs after RoleSeeder which has @Order(1)
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
        this.createAdministrator();
    }

    private void createSuperAdministrator() {
        this.createAdminUser("Super Admin", "super_admin@mail.com", RoleEnum.SUPER_ADMIN);
    }

    private void createAdministrator() {
        this.createAdminUser("Admin", "admin@mail.com", RoleEnum.ADMIN);
    }

    private void createAdminUser(String name, String email, RoleEnum roleEnum) {
        Optional<Role> optionalRole = roleRepository.findByName(roleEnum);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = User.builder()
                .fullName(name)
                .email(email)
                .password(passwordEncoder.encode("111111"))
                .role(optionalRole.get())
                .build();

        userRepository.save(user);
    }
}