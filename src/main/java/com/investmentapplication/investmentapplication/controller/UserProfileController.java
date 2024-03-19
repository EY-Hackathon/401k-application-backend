package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserProfileDto;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserProfileController implements UserProfileOperations {

    @Autowired
    private UserProfileService userProfileService;

    public ResponseEntity<Object> getUserProfile(String email) throws Exception {
        UserProfileDto userProfileDto = userProfileService.getUserProfileDetails(email);
        return ResponseEntity.ok(userProfileDto);
    }

    public ResponseEntity<Object> updateUserProfile(String email, UserProfileDto userProfile) throws Exception {
        userProfileService.updateUserProfile(email, userProfile);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }
}
