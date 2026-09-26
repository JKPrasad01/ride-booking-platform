package com.ridebooking.ride_booking_backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class RegisterRequestDTO {

    @NotBlank(message = "name is required")
    @Size(min = 4, max = 50, message = "name must be between 4 and 50 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    private String email;

    @NotBlank(message = "contact is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "contact must be a valid 10-digit number")
    private String contact;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 100, message = "password must be at least 8 characters")
    private String password;

    @NotBlank(message = "role is required")
    @Pattern(regexp = "^(PASSENGER|DRIVER)$", message = "role must be PASSENGER or DRIVER")
    private String role;
}