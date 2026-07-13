package com.abhishek.zerotrustiam.controller;

import com.abhishek.zerotrustiam.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/status")
    public ApiResponse status() {
        return new ApiResponse("Admin access verified.");
    }
}
