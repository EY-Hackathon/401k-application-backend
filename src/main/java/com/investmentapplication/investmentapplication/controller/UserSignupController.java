package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.services.UserSignUpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Validated
@CrossOrigin
@RequestMapping("/api")
public class UserSignupController {

    @Autowired
    private UserSignUpServices userSignUpServices;

    @PostMapping(path = "/signup")

    public ResponseEntity<String> signup(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            // Check for missing fields and extract error messages
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage) // Get the error message defined in the annotation
                    .collect(Collectors.toList());

            if (!errorMessages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(", ", errorMessages));
            }
        }
        if (userSignUpServices.isEmailexists(userSignUpDTO.getEmail() )) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
            userSignUpServices.addUser(userSignUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
