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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling user sign-up related API endpoints.
 */
@RestController
@Validated
@CrossOrigin
@RequestMapping("/api")
public class UserSignupController{

    @Autowired
    private UserSignUpServices userSignUpServices;

    public ResponseEntity<String> signup(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            // Check for validation errors and return appropriate response
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage) // Get the error message defined in the annotation
                    .collect(Collectors.toList());

            if (!errorMessages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(", ", errorMessages));
            }
        }
        // Check if the email already exists
        if (userSignUpServices.isEmailExists(userSignUpDTO.getEmail() )) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
        // Add the user if email doesn't exist and validation passes
        userSignUpServices.addUser(userSignUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
