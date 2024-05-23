package com.dietreino.backend.services;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.LoginRequestDTO;
import com.dietreino.backend.dto.user.UserDTO;
import com.dietreino.backend.dto.user.UserListActivePlanWorkout;
import com.dietreino.backend.dto.user.UserResponse;
import com.dietreino.backend.dto.workout.ActiveWorkout;
import com.dietreino.backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public UserResponse findById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .lastName(user.getLastName())
                .activeWorkoutId(Optional.ofNullable(user.getActiveWorkout()).map(Workout::getId).orElse(null))
                .planStart(Optional.ofNullable(user.getPlanStart()).map(Object::toString).orElse(null))
                .birthDate(Optional.ofNullable(user.getDateOfBirth()).map(Object::toString).orElse(null))
                .planExpiration(Optional.ofNullable(user.getPlanExpiration()).map(Object::toString).orElse(null))
                .nextAppointment(Optional.ofNullable(user.getNextAppointment()).map(Object::toString).orElse(null))
                .workoutExpiration(Optional.ofNullable(user.getWorkoutExpiration()).map(Object::toString).orElse(null))
                .build();
    }

    public ActiveWorkout getActiveWorkout(UUID userId) {
        Optional<Workout> workout = userRepository.findActiveWorkoutByUserId(userId);
        return ActiveWorkout.builder().workout(workout.orElse(null)).build();
    }

    public List<UserListActivePlanWorkout> getUsersWithActivePlanAndWorkout() {
        return userRepository
                .findActiveUsersWithActivePlan()
                .stream()
                .map(user -> UserListActivePlanWorkout
                        .builder()
                        .planExpiration(user.getPlanExpiration().toString())
                        .planStart(user.getPlanStart().toString())
                        .workoutExpiration(user.getWorkoutExpiration().toString())
                        .nextAppointment(user.getNextAppointment().toString())
                        .id(user.getId())
                        .name(user.getName())
                        .build()
                )
                .toList();
    }
}
