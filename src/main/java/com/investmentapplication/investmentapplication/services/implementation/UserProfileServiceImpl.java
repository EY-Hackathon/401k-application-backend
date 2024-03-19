package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserProfileDto;
import com.investmentapplication.investmentapplication.entity.EmploymentDetails;
import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.repository.EmploymentDetailsRepository;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmploymentDetailsRepository employmentDetailsRepository;

    public UserProfileDto getUserProfileDetails(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        EmploymentDetails employmentDetails = employmentDetailsRepository.findByEmail(email);
        UserProfileDto userProfile = new UserProfileDto();
        if (userEntity != null) {
            userProfile.setUsername(userEntity.getUsername());
            userProfile.setFirstname(userEntity.getFirstname());
            userProfile.setLastname(userEntity.getLastname());
            userProfile.setMailingaddress(userEntity.getMailingaddress());
            userProfile.setPhoneNumber(userEntity.getPhoneNo());
            userProfile.setDateofbirth(userEntity.getDateofbirth());
            userProfile.setSsn(userEntity.getSsn());
        } else {
            throw new Exception("User profile with user details not found for username: " + email);
        }
        if(employmentDetails!=null){
            userProfile.setUsername(employmentDetails.getUsername());
            userProfile.setEmail(employmentDetails.getEmail());
            userProfile.setAnnual_salary(employmentDetails.getAnnualSalary());
            userProfile.setPayfrequency(employmentDetails.getPayFrequency());
            userProfile.setEmployername(employmentDetails.getEmployerName());
            userProfile.setEmployment_start_date(employmentDetails.getEmploymentStartDate());
        }
        else {
            throw new Exception("User profile with employment details not found for username: " + email);
        }
        return userProfile;
    }

    public void updateUserProfile(String email, UserProfileDto userProfile) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            userEntity.setFirstname(userProfile.getFirstname());
            userEntity.setLastname(userProfile.getLastname());
            userEntity.setMailingaddress(userProfile.getMailingaddress());
            userEntity.setPhoneNo(userProfile.getPhoneNumber());
            userEntity.setDateofbirth(userProfile.getDateofbirth());
            userEntity.setSsn(userProfile.getSsn());
            userRepository.save(userEntity);
            EmploymentDetails employmentDetails = employmentDetailsRepository.findByEmail(email);
        if (employmentDetails != null) {
            employmentDetails.setEmployerName(userProfile.getEmployername());
            employmentDetails.setEmploymentStartDate(userProfile.getEmployment_start_date());
            employmentDetails.setAnnualSalary(userProfile.getAnnual_salary());
            employmentDetails.setPayFrequency(userProfile.getPayfrequency());
            employmentDetailsRepository.save(employmentDetails);
        } else {
            throw new Exception("Employment details not found for username: " + email);
        }
        } else {
            throw new UsernameNotFoundException("User profile not found for username: " + email);
        }
        }
}
