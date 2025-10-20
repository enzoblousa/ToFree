package com.inflatable.freelancer.service;

import com.inflatable.freelancer.dto.AuthRequest;
import com.inflatable.freelancer.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    String generateToken(String email);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}