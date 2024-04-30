package com.dietreino.backend.services;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.dto.LoginRequestDTO;
import com.dietreino.backend.dto.UserDTO;
import com.dietreino.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void validateDuplicatedUser(String email) {
        Optional<User> duplicatedUser = this.userRepository.findByEmail(email);

        if (duplicatedUser.isPresent()) {
            throw new IllegalArgumentException("Email address already exists");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void validateEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    private void validateUserDto(UserDTO userDto) {
        if (userDto.name() == null || userDto.name().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        validateEmail(userDto.email());
    }

    private User mapDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return user;
    }

    public User save(UserDTO userDTO) {
        validateUserDto(userDTO);
        validateDuplicatedUser(userDTO.email());
        User userToSave = mapDtoToEntity(userDTO);
        this.userRepository.save(userToSave);

        return userToSave;
    }

    public User verifyPassword(LoginRequestDTO loginRequestDTO) {
        validateEmail(loginRequestDTO.email());
        User user = this.userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return user;
    }
}
