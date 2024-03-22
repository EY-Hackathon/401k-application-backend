package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserProfileDto;
import com.investmentapplication.investmentapplication.entity.EmploymentDetailsEntity;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.exception.EmploymentDetailsException;
import com.investmentapplication.investmentapplication.exception.UserNotFoundException;
import com.investmentapplication.investmentapplication.repository.EmploymentDetailsRepository;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    /*this class is used to fetch values from employment and user table and also update the values in those tables*/
    @Autowired
    private UserAccountsRepository userRepository;

    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepository;

    //Fetch values from user and employment table
    @Override
    public UserProfileDto getUserProfileDetails(String email) {
        List<UserAccountsEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity == null || userEntity.isEmpty()) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND, "User profile not found for email: " + email);
        }//user details not found for the email id

        EmploymentDetailsEntity employmentDetails = employmentDetailsRepository.findByEmail(email);
        if (employmentDetails == null) {
            throw new EmploymentDetailsException(ErrorCode.EMPLOYMENT_DETAILS_NOT_FOUND, "Employment details not found for email: " + email);
        }//employment details not found for the user
        UserProfileDto userProfile = new UserProfileDto();
        userEntity.forEach(users -> {
            userProfile.setUserName(users.getUsername());
            userProfile.setFirstName(users.getFirstName());
            userProfile.setLastName(users.getLastName());
            userProfile.setMailingAddress(users.getMailingAddress());
            userProfile.setPhoneNumber(users.getPhoneNumber());
            userProfile.setDateOfBirth(users.getDateOfBirth());
            userProfile.setSsn(users.getSsn());
        });

        if (employmentDetails.getEmployerName() == null || employmentDetails.getEmail() == null
                || employmentDetails.getPayFrequency() == null
                || employmentDetails.getEmploymentStartDate() == null) {
            throw new EmploymentDetailsException(ErrorCode.INVALID_DATA, "Some employment details are missing or empty for email: " + email);
        }// if data is null we need to throw exception

        userProfile.setEmployerName(employmentDetails.getEmployerName());
        userProfile.setEmail(employmentDetails.getEmail());
        userProfile.setAnnualSalary(employmentDetails.getAnnualSalary());
        userProfile.setPayFrequency(employmentDetails.getPayFrequency());
        userProfile.setEmploymentStartDate(employmentDetails.getEmploymentStartDate());

        return userProfile;
    }

    @Override
    public void updateUserProfile(String email, UserProfileDto userProfile) {
        List<UserAccountsEntity> userEntity = userRepository.findByEmail(email);
        userEntity.forEach(users -> {
            users.setFirstName(userProfile.getFirstName() != null ? userProfile.getFirstName() : null);
            users.setLastName(userProfile.getLastName() != null ? userProfile.getLastName() : null);
            users.setMailingAddress(userProfile.getMailingAddress() != null ? userProfile.getMailingAddress() : null);
            users.setPhoneNumber(userProfile.getPhoneNumber() != null ? userProfile.getPhoneNumber() : null);
            users.setDateOfBirth(userProfile.getDateOfBirth() != null ? userProfile.getDateOfBirth() : null);
            users.setSsn(String.valueOf(userProfile.getSsn()).length() == 9 ? userProfile.getSsn() : null);
            userRepository.save(users);
        });
        //save values in user repository

        EmploymentDetailsEntity employmentDetails = employmentDetailsRepository.findByEmail(email);
        employmentDetails.setEmployerName(userProfile.getEmployerName() != null ? userProfile.getEmployerName() : null);
        employmentDetails.setEmploymentStartDate(userProfile.getEmploymentStartDate() != null ? userProfile.getEmploymentStartDate() : null);
        employmentDetails.setAnnualSalary(userProfile.getAnnualSalary() != null ? userProfile.getAnnualSalary() : 0);
        employmentDetails.setPayFrequency(userProfile.getPayFrequency() != null ? userProfile.getPayFrequency() : null);
        employmentDetailsRepository.save(employmentDetails); //save values in employmentDetails repository
    }
}

