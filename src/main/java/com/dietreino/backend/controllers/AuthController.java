package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.dto.LoginRequestDTO;
import com.dietreino.backend.dto.UserDTO;
import com.dietreino.backend.services.TokenService;
import com.dietreino.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = userService.verifyPassword(body);
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO body) {
        User newUser = userService.save(body);
        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(token);
    }
}
