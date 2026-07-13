package com.abhishek.zerotrustiam.dto;

import java.util.Set;

public record UserProfileResponse(String email, Set<String> roles) {}
