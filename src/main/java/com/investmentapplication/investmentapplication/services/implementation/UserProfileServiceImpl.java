package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.EmploymentDetails;
import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.entity.UserProfile;
import com.investmentapplication.investmentapplication.repository.EmploymentDetailsRepository;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepository;

    public UserProfile getUserProfileDetails(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        EmploymentDetails employmentDetails= employmentDetailsRepository.findByUsername(username);
        UserProfile userProfile = new UserProfile();
        if (userEntity != null) {
            userProfile.setUsername(userEntity.getUsername());
            userProfile.setFirstname(userEntity.getFirstname());
            userProfile.setDob(userEntity.getDob());
            userProfile.setSsn(userEntity.getSsn());
        } else {
            throw new RuntimeException("User profile with user details not found for username: " + username);
        }
        if(employmentDetails!=null){
            userProfile.setEmployername(employmentDetails.getEmployername());
            userProfile.setEmployment_start_date(employmentDetails.getEmployment_start_date());
        }
        else {
            throw new RuntimeException("User profile with employment details not found for username: " + username);
        }
        return userProfile;
    }

    public void updateUserProfile(String username, UserProfile userProfile) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            userEntity.setFirstname(userProfile.getFirstname());
            userEntity.setDob(userProfile.getDob());
            userEntity.setSsn(userProfile.getSsn());
            userRepository.save(userEntity);
            EmploymentDetails employmentDetails = employmentDetailsRepository.findByUsername(username);
            if (employmentDetails != null) {
                employmentDetails.setEmployername(userProfile.getEmployername());
                employmentDetails.setEmployment_start_date(userProfile.getEmployment_start_date());
                employmentDetailsRepository.save(employmentDetails);
            } else {
                throw new RuntimeException("Employment details not found for username: " + username);
            }
        } else {
            throw new RuntimeException("User profile not found for username: " + username);
        }
        }
}
