package com.dietreino.backend.dto.user;

import lombok.Builder;

@Builder
public record UserRegisterResponse(String token, String temporaryPassword) {
}
