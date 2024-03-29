package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


public interface UserSignUpOperations {
    @PostMapping("/signup")
    ResponseEntity<TokenResponse> signup(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult) throws Exception;

}
