package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity request) {
        TokenResponse response= userService.loginService(request);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            System.out.println(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed").toString());
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }
}
