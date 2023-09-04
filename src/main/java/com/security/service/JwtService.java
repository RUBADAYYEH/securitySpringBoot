package com.security.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
public interface JwtService {
    String extractUserName(String token);
    String generateToken (UserDetails userDetails);
    boolean isTokenValid(String token,UserDetails userDetails);
}
