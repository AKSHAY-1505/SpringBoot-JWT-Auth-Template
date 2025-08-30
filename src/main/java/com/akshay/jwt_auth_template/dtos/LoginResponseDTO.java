package com.akshay.jwt_auth_template.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private String token;

    private long expiresIn;
}
