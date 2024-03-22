package com.investmentapplication.investmentapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface LoginOperations {

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody UserEntity request);
}
