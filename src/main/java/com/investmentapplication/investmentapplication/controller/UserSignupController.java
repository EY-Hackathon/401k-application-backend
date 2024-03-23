package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.services.UserSignUpServices;
import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.exception.UserAlreadyExistsException;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/api")
public class UserSignupController implements UserSignUpOperations {

    @Autowired
    private UserSignUpServices userSignUpServices;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signup(@RequestBody @Validated UserSignUpDTO userSignUpDTO, BindingResult bindingResult) {
        TokenResponse response = new TokenResponse(); // Create a new token response
        try {
            if (bindingResult.hasErrors()) {
                // Check for validation errors and return appropriate response
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage) // Get the error message defined in the annotation
                        .collect(Collectors.toList());
                String responseMessage = String.join(",", errorMessages);
                response.setMessage(responseMessage);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            else {

                // Call the service method to add user
                TokenResponse tokenResponse =userSignUpServices.addUser(userSignUpDTO);
                response.setMessage(tokenResponse.getMessage());
            }
        } catch (UserAlreadyExistsException e) {
            // Handle user already exists exception
            response.setErrorCode(ErrorCode.USER_ALREADY_EXISTS);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        // Return response with OK status
        return new ResponseEntity<TokenResponse>(response, HttpStatus.OK);
    }
}
