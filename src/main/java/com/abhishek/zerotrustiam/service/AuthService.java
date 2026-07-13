package com.abhishek.zerotrustiam.service;

import com.abhishek.zerotrustiam.dto.AuthResponse;
import com.abhishek.zerotrustiam.dto.LoginRequest;
import com.abhishek.zerotrustiam.dto.RegisterRequest;
import com.abhishek.zerotrustiam.entity.Role;
import com.abhishek.zerotrustiam.entity.UserAccount;
import com.abhishek.zerotrustiam.repository.UserAccountRepository;
import com.abhishek.zerotrustiam.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordPolicyService passwordPolicyService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuditService auditService;

    public AuthService(UserAccountRepository repository, PasswordEncoder passwordEncoder,
                       PasswordPolicyService passwordPolicyService, AuthenticationManager authenticationManager,
                       JwtService jwtService, AuditService auditService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.passwordPolicyService = passwordPolicyService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.auditService = auditService;
    }

    public void register(RegisterRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered.");
        }
        passwordPolicyService.validate(request.password(), request.email());
        var account = new UserAccount(request.email().toLowerCase(), passwordEncoder.encode(request.password()), Set.of(Role.USER));
        repository.save(account);
        auditService.record("USER_REGISTERED", request.email(), true, "New user account created");
    }

    public AuthResponse login(LoginRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email().toLowerCase(), request.password())
            );
            String token = jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) auth.getPrincipal());
            auditService.record("LOGIN", request.email(), true, "JWT issued");
            return new AuthResponse("Bearer", token, jwtService.getExpirationInstant());
        } catch (RuntimeException ex) {
            auditService.record("LOGIN", request.email(), false, "Invalid authentication attempt");
            throw ex;
        }
    }
}
