package com.investmentapplication.investmentapplication.services;


import com.investmentapplication.investmentapplication.dto.UserProfileDto;

public interface UserProfileService {
    UserProfileDto getUserProfileDetails(String email) throws Exception;
    void updateUserProfile(String username, UserProfileDto userProfile) throws Exception;
}

