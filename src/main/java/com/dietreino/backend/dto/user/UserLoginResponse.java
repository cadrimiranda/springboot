package com.dietreino.backend.dto.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserLoginResponse(UUID id, String name, String lastName, String fullName, String email) {
}
