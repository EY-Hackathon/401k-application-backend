package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.services.UserSignUpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserSignupController {

    @Autowired
    private UserSignUpServices userSignUpServices;

    @PostMapping(path = "/signup")
//    public void saveUser(@RequestBody UserSignUpDTO userSignUpDTO)
//    {
//         userSignUpServices.addUser(userSignUpDTO);
//     }
    public ResponseEntity<String> signup(@RequestBody UserSignUpDTO userSignUpDTO) throws Exception {
        if (userSignUpServices.isEmailexists(userSignUpDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
            userSignUpServices.addUser(userSignUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
