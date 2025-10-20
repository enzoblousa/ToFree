package com.inflatable.freelancer;

import com.inflatable.freelancer.dto.UserDTO;
import com.inflatable.freelancer.model.User;
import com.inflatable.freelancer.model.Role;
import com.inflatable.freelancer.repository.UserRepository;
import com.inflatable.freelancer.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@email.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.CLIENT);
    }

    @Test
    void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        
        List<UserDTO> users = userService.findAll();
        
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("Test User", users.get(0).getName());
    }

    @Test
    void shouldFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        UserDTO foundUser = userService.findById(1L);
        
        assertNotNull(foundUser);
        assertEquals("Test User", foundUser.getName());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.existsByEmail("test@email.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User newUser = new User();
        newUser.setName("Test User");
        newUser.setEmail("test@email.com");
        newUser.setPassword("password");
        newUser.setRole(Role.CLIENT);
        
        UserDTO createdUser = userService.create(newUser);
        
        assertNotNull(createdUser);
        assertEquals("Test User", createdUser.getName());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepository.existsByEmail("test@email.com")).thenReturn(true);
        
        User newUser = new User();
        newUser.setEmail("test@email.com");
        
        assertThrows(RuntimeException.class, () -> userService.create(newUser));
    }
}