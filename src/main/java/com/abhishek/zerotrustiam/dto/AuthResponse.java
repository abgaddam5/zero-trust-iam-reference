package com.abhishek.zerotrustiam.dto;

import java.time.Instant;

public record AuthResponse(String tokenType, String accessToken, Instant expiresAt) {}
