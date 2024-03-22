package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserProfileDto;
import com.investmentapplication.investmentapplication.entity.EmploymentDetailsEntity;
import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.exception.EmploymentDetailsException;
import com.investmentapplication.investmentapplication.exception.UserNotFoundException;
import com.investmentapplication.investmentapplication.repository.EmploymentDetailsRepository;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    /*this class is used to fetch values from employment and user table and also update the values in those tables*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmploymentDetailsRepository employmentDetailsRepository;

    //Fetch values from user and employment table
    @Override
    public UserProfileDto getUserProfileDetails(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND, "User profile not found for email: " + email);
        }//user details not found for the email id

        EmploymentDetailsEntity employmentDetails = employmentDetailsRepository.findByEmail(email);
        if (employmentDetails == null) {
            throw new EmploymentDetailsException(ErrorCode.EMPLOYMENT_DETAILS_NOT_FOUND, "Employment details not found for email: " + email);
        }//employment details not found for the user

        UserProfileDto userProfile = new UserProfileDto();
        userProfile.setUsername(userEntity.getUsername());
        userProfile.setFirstname(userEntity.getFirstname());
        userProfile.setLastname(userEntity.getLastname());
        userProfile.setMailingaddress(userEntity.getMailingaddress());
        userProfile.setPhoneNumber(userEntity.getPhonenumbeer());
        userProfile.setDateofbirth(userEntity.getDateofbirth());
        userProfile.setSsn(userEntity.getSsn());

        if (employmentDetails.getEmployerName() == null || employmentDetails.getEmail() == null
                || employmentDetails.getPayFrequency() == null
                || employmentDetails.getEmploymentStartDate() == null) {
            throw new EmploymentDetailsException(ErrorCode.INVALID_DATA, "Some employment details are missing or empty for email: " + email);
        }// if data is null we need to throw exception

        userProfile.setEmployername(employmentDetails.getEmployerName());
        userProfile.setEmail(employmentDetails.getEmail());
        userProfile.setAnnual_salary(employmentDetails.getAnnualSalary());
        userProfile.setPayfrequency(employmentDetails.getPayFrequency());
        userProfile.setEmployment_start_date(employmentDetails.getEmploymentStartDate());

        return userProfile;
    }

    @Override
    public void updateUserProfile(String email, UserProfileDto userProfile) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND, "User profile not found for email: " + email);
        }
        userEntity.setFirstname(userProfile.getFirstname());
        userEntity.setLastname(userProfile.getLastname());
        userEntity.setMailingaddress(userProfile.getMailingaddress());
        userEntity.setPhonenumbeer(userProfile.getPhoneNumber());
        userEntity.setDateofbirth(userProfile.getDateofbirth());
        userEntity.setSsn(userProfile.getSsn());
        userRepository.save(userEntity); //save values in user repository

        EmploymentDetailsEntity employmentDetails = employmentDetailsRepository.findByEmail(email);
        if (employmentDetails == null) {
            throw new EmploymentDetailsException(ErrorCode.EMPLOYMENT_DETAILS_NOT_FOUND, "Employment details not found for email: " + email);
        }
        employmentDetails.setEmployerName(userProfile.getEmployername());
        employmentDetails.setEmploymentStartDate(userProfile.getEmployment_start_date());
        employmentDetails.setAnnualSalary(userProfile.getAnnual_salary());
        employmentDetails.setPayFrequency(userProfile.getPayfrequency());
        employmentDetailsRepository.save(employmentDetails); //save values in employmentDetails repository
    }


}