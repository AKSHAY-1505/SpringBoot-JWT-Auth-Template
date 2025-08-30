package com.akshay.jwt_auth_template.services;

import com.akshay.jwt_auth_template.dtos.RegisterUserDto;
import com.akshay.jwt_auth_template.entities.Role;
import com.akshay.jwt_auth_template.entities.RoleEnum;
import com.akshay.jwt_auth_template.entities.User;
import com.akshay.jwt_auth_template.repositories.RoleRepository;
import com.akshay.jwt_auth_template.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }
}
