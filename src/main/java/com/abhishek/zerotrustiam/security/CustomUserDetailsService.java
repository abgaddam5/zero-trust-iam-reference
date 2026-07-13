package com.abhishek.zerotrustiam.security;

import com.abhishek.zerotrustiam.repository.UserAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository repository;

    public CustomUserDetailsService(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var account = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
        return User.withUsername(account.getEmail())
                .password(account.getPasswordHash())
                .authorities(authorities)
                .disabled(!account.isEnabled())
                .build();
    }
}
