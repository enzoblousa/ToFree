package com.inflatable.freelancer;

import com.inflatable.freelancer.dto.AuthRequest;
import com.inflatable.freelancer.dto.AuthResponse;
import com.inflatable.freelancer.model.User;
import com.inflatable.freelancer.model.Role;
import com.inflatable.freelancer.repository.UserRepository;
import com.inflatable.freelancer.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldAuthenticateUser() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setName("Test User");
        user.setRole(Role.CLIENT);

        AuthRequest authRequest = new AuthRequest("test@email.com", "password");

        when(userRepository.findByEmail("test@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        AuthResponse response = authService.authenticate(authRequest);

        assertNotNull(response);
        assertEquals("Test User", response.getName());
        assertEquals("test@email.com", response.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        AuthRequest authRequest = new AuthRequest("nonexistent@email.com", "password");
        
        when(userRepository.findByEmail("nonexistent@email.com")).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> authService.authenticate(authRequest));
    }

    @Test
    void shouldThrowExceptionWhenPasswordInvalid() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");

        AuthRequest authRequest = new AuthRequest("test@email.com", "wrongpassword");

        when(userRepository.findByEmail("test@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.authenticate(authRequest));
    }

    @Test
    void shouldGenerateToken() {
        String token = authService.generateToken("test@email.com");
        
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void shouldValidateToken() {
        String token = authService.generateToken("test@email.com");
        boolean isValid = authService.validateToken(token);
        
        assertTrue(isValid);
    }
}