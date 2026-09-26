package com.ridebooking.ride_booking_backend.auth.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
public class AuthResponseDTO {
    private String token;
    private Long userId;
    private String name;
    private String email;
    private List<String> roles;
}
