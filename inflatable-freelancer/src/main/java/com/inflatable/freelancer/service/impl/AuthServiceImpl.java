package com.inflatable.freelancer.service.impl;

import com.inflatable.freelancer.dto.AuthRequest;
import com.inflatable.freelancer.dto.AuthResponse;
import com.inflatable.freelancer.model.User;
import com.inflatable.freelancer.repository.UserRepository;
import com.inflatable.freelancer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret:mySecretKey}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400000}")
    private Long jwtExpiration;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        // Para desenvolvimento, vamos usar o email como token
        // Em produção, implemente JWT proper
        String token = user.getEmail(); 
        
        return new AuthResponse(
            token, 
            user.getEmail(), 
            user.getName(), 
            user.getRole().name()
        );
    }

    @Override
    public String generateToken(String email) {
        // Implementação simples para desenvolvimento
        return email;
    }

    @Override
    public boolean validateToken(String token) {
        // Implementação simples para desenvolvimento
        return token != null && token.contains("@");
    }

    @Override
    public String getUsernameFromToken(String token) {
        // Implementação simples para desenvolvimento
        return token;
    }
}