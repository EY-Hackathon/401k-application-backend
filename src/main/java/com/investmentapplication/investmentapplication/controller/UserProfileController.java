package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.entity.UserProfile;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    public class UserProfileController {

        @Autowired
        private UserProfileService userProfileService;

        @GetMapping("/profile/{username}")
        public ResponseEntity<UserProfile> getUserProfile(@PathVariable String username) {
            UserProfile userProfile = userProfileService.getUserProfileDetails(username);
            if (userProfile != null) {
                return ResponseEntity.ok(userProfile);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @PutMapping("/profile/{username}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable String username, @RequestBody UserProfile userProfile) {
        userProfileService.updateUserProfile(username, userProfile);
        return ResponseEntity.noContent().build();
    }
}
