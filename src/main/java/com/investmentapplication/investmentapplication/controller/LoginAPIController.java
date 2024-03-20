package com.investmentapplication.investmentapplication.controller;
//package com.investmentapplication.investmentapplication.controller;
//
//import com.investmentapplication.investmentapplication.entity.UserEntity;
//import com.investmentapplication.investmentapplication.repository.UserRepository;
//import com.investmentapplication.investmentapplication.services.implementation.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoginAPIController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private LoginService loginService;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
//        if (loginService.login(userEntity.getUsername(), userEntity.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
//        if (loginService.register(userEntity.getUsername(), userEntity.getPassword())) {
//            return ResponseEntity.ok("Registration successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//        }
//    }
//    }
//

import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.filter.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginAPIController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserEntity authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok("testing login ");
    }
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(" you have access now  ");
    }
}
