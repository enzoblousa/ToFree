package com.inflatable.freelancer;

import com.inflatable.freelancer.dto.AuthRequest;
import com.inflatable.freelancer.dto.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldAuthenticateWithValidCredentials() {
        AuthRequest authRequest = new AuthRequest("admin@email.com", "admin123");
        
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
            "/api/auth/login", authRequest, AuthResponse.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getToken());
    }

    @Test
    void shouldReturnUnauthorizedWithInvalidCredentials() {
        AuthRequest authRequest = new AuthRequest("admin@email.com", "wrongpassword");
        
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
            "/api/auth/login", authRequest, AuthResponse.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}