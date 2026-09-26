package com.ridebooking.ride_booking_backend.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ridebooking.ride_booking_backend.user.User;
import com.ridebooking.ride_booking_backend.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService{
    
    private final UserRepository userRepository;

    @Override 
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email)
        .orElseThrow(()->new UsernameNotFoundException("User not found: " + email));

        return new CustomUserDetails(user);
    }
}
