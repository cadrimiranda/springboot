package com.dietreino.backend.services;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.LoginRequestDTO;
import com.dietreino.backend.dto.user.UserListActivePlanWorkout;
import com.dietreino.backend.dto.user.UserRequestDTO;
import com.dietreino.backend.dto.user.UserResponse;
import com.dietreino.backend.exceptions.WorkoutWithoutUser;
import com.dietreino.backend.repositories.UserRepository;
import com.dietreino.backend.utils.DateUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PASSWORD_LENGTH = 12;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String generateRandomPassword() {
        byte[] randomBytes = new byte[PASSWORD_LENGTH];
        RANDOM.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
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

    private void validateUserDto(UserRequestDTO userDto) {
        if (userDto.name() == null || userDto.name().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        validateEmail(userDto.email());
    }

    private User mapDtoToEntity(UserRequestDTO userDTO, String randomPassword) {
        Date planStartDate = DateUtils.parse(userDTO.planStart());

        return User.builder()
                .email(userDTO.email())
                .password(passwordEncoder.encode(randomPassword))
                .email(userDTO.email())
                .name(userDTO.name())
                .lastName(userDTO.lastName())
                .activeWorkout(null)
                .dateOfBirth(DateUtils.parse(userDTO.birthDate()))
                .phone(userDTO.phone())
                .planExpiration(DateUtils.parse(userDTO.planExpiration()))
                .planStart(planStartDate != null ? planStartDate : Date.from(Instant.now()))
                .shouldChangePassword(true)
                .build();
    }

    public UserResponse toResponseDTO(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .lastName(user.getLastName())
                .fullName(user.getName() + ' ' + user.getLastName())
                .activeWorkoutId(Optional.ofNullable(user.getActiveWorkout()).map(Workout::getId).orElse(null))
                .planStart(Optional.ofNullable(user.getPlanStart()).map(Object::toString).orElse(null))
                .birthDate(Optional.ofNullable(user.getDateOfBirth()).map(Object::toString).orElse(null))
                .planExpiration(Optional.ofNullable(user.getPlanExpiration()).map(Object::toString).orElse(null))
                .nextAppointment(Optional.ofNullable(user.getNextAppointment()).map(Object::toString).orElse(null))
                .workoutExpiration(Optional.ofNullable(user.getWorkoutExpiration()).map(Object::toString).orElse(null))
                .build();
    }

    public void addActiveWorkout(UUID userId, Workout workout) {
        User domainUser = this.getDomainUser(userId);
        domainUser.setActiveWorkout(workout);

        List<Workout> workouts = domainUser.getWorkouts();
        workouts.add(workout);
        domainUser.setWorkouts(workouts);

        this.toResponseDTO(userRepository.save(domainUser));
    }

    public User save(UserRequestDTO userDTO) {
        validateUserDto(userDTO);
        validateDuplicatedUser(userDTO.email());
        String randomPassword = this.generateRandomPassword();

        User userToSave = mapDtoToEntity(userDTO, randomPassword);
        User savedUser = userRepository.save(userToSave);
        savedUser.setPassword(randomPassword);
        return savedUser;
    }

    public User verifyPassword(LoginRequestDTO loginRequestDTO) {
        validateEmail(loginRequestDTO.email());
        User user = this.userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return user;
    }

    public User getDomainUser(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

    }

    public UserResponse findById(UUID id) {
        User user = this.getDomainUser(id);
        return this.toResponseDTO(user);
    }

    public Workout getActiveWorkout(UUID userId) {
        Optional<Workout> workout = userRepository.findActiveWorkoutByUserId(userId);
        return workout.orElse(null);
    }

    public List<UserListActivePlanWorkout> getUsersWithActivePlanAndWorkout() {
        return userRepository
                .findActiveUsersWithActivePlan()
                .stream()
                .map(user -> UserListActivePlanWorkout
                        .builder()
                        .planExpiration(user.getPlanExpiration().toString())
                        .planStart(user.getPlanStart().toString())
                        .workoutExpiration(Optional.ofNullable(user.getActiveWorkout())
                                .map(workout -> workout.getEndDate().toString())
                                .orElse(null))
                        .nextAppointment(user.getNextAppointment().toString())
                        .id(user.getId())
                        .name(user.getName())
                        .build()
                )
                .toList();
    }

    public void removeUserWorkout(UUID workoutId) {
        Optional<User> user = userRepository.findUserByActiveWorkoutId(workoutId);
        if (user.isEmpty()) {
            throw new WorkoutWithoutUser(workoutId);
        }

        User userWorkout = user.get();
        userWorkout.setActiveWorkout(null);
        userWorkout.getWorkouts().removeIf(w -> w.getId().equals(workoutId));
        userRepository.save(userWorkout);
    }
}
