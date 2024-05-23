package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.dto.LoginRequestDTO;
import com.dietreino.backend.dto.user.UserRequestDTO;
import com.dietreino.backend.services.TokenService;
import com.dietreino.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AuthController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO body) {
        User user = userService.verifyPassword(body);
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO body) {
        User newUser = userService.save(body);
        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(token);
    }
}
