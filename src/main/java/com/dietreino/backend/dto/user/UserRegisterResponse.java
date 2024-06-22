package com.dietreino.backend.dto.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisterResponse(String token, String temporaryPassword, UUID id) {
}
