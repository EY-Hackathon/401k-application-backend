package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")

public interface UserProfileOperations {

    @GetMapping("/profile")
    ResponseEntity<Object> getUserProfile(@RequestParam String email) throws Exception;

    @PutMapping("/profile")
    ResponseEntity<Object> updateUserProfile(@RequestParam String userId, @RequestBody UserProfileDto userProfile) throws Exception;
}
