package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController implements LoginOperations{
    @Autowired
    UserService userService;


    public ResponseEntity<Object> login(UserAccountsEntity request) throws UsernameNotFoundException {
        TokenResponse response= userService.loginService(request);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
