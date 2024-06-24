package com.dietreino.backend.dto.login;

import com.dietreino.backend.dto.user.UserLoginResponse;
import lombok.Builder;

import java.util.Date;

@Builder
public record LoginResponseDTO(String token, String refreshToken, Integer timeToExpire, Date expiresAt,
                               UserLoginResponse user) {
}
