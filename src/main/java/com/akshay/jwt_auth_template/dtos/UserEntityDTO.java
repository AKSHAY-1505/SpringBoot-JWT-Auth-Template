package com.akshay.jwt_auth_template.dtos;


import com.akshay.jwt_auth_template.entities.User;
import lombok.Getter;

@Getter
public class UserEntityDTO {
    private String email;
    private String fullName;

    public UserEntityDTO(User user) {
        this.email = user.getEmail();
        this.fullName = user.getFullName();
    }
}
