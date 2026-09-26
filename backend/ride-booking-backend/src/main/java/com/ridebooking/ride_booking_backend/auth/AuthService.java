package com.ridebooking.ride_booking_backend.auth;

import com.ridebooking.ride_booking_backend.auth.dto.AuthResponseDTO;
import com.ridebooking.ride_booking_backend.auth.dto.LoginRequestDTO;
import com.ridebooking.ride_booking_backend.auth.dto.RegisterRequestDTO;
import com.ridebooking.ride_booking_backend.auth.security.JwtTokenProvider;
import com.ridebooking.ride_booking_backend.common.exception.DuplicateResourceException;
import com.ridebooking.ride_booking_backend.common.exception.InvalidCredentialsException;
import com.ridebooking.ride_booking_backend.common.exception.ResourceNotFoundException;
import com.ridebooking.ride_booking_backend.user.Role;
import com.ridebooking.ride_booking_backend.user.RoleRepository;
import com.ridebooking.ride_booking_backend.user.User;
import com.ridebooking.ride_booking_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getRole()));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getContact());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus("ACTIVE");

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        User saved = userRepository.save(user);

        String token = jwtTokenProvider.generateToken(
                saved.getId(), saved.getEmail(),
                saved.getRoles().stream().map(Role::getName).toList());

        return toResponseDTO(saved, token);
    }

    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(
                user.getId(), user.getEmail(),
                user.getRoles().stream().map(Role::getName).toList());

        return toResponseDTO(user, token);
    }

    private AuthResponseDTO toResponseDTO(User user, String token) {
        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setToken(token);
        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream().map(Role::getName).toList());
        return dto;
    }
}