package com.investmentapplication.investmentapplication.services;


import com.investmentapplication.investmentapplication.entity.UserProfile;

public interface UserProfileService {
    UserProfile getUserProfileDetails(String username);
    void updateUserProfile(String username, UserProfile userProfile);
}

