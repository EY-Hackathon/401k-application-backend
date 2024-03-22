package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.UserSignUpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpServicesImpl implements UserSignUpServices {

    @Autowired
    private UserAccountsRepository userAccountsRepository;

    @Autowired
    private UserEmploymentRepository userEmploymentRepository;


    @Override
    public void addUser(UserSignUpDTO userSignUpDTO) {

        UserAccountsEntity userAccountsEntity = new UserAccountsEntity();
        userAccountsEntity.setFirstName(userSignUpDTO.getFirstName());
        userAccountsEntity.setLastName(userSignUpDTO.getLastName());
        userAccountsEntity.setDateOfBirth(userSignUpDTO.getDateOfBirth());
        userAccountsEntity.setSsn(userSignUpDTO.getSsn());
        userAccountsEntity.setMailingAddress(userSignUpDTO.getMailingAddress());
        userAccountsEntity.setEmail(userSignUpDTO.getEmail());
        userAccountsEntity.setPhoneNumber(userSignUpDTO.getPhoneNumber());
        userAccountsEntity.setPassword(userSignUpDTO.getPassword());
        userAccountsRepository.save(userAccountsEntity);

        UserEmploymentEntity userEmploymentEntity = new UserEmploymentEntity();
        userEmploymentEntity.setEmail(userSignUpDTO.getEmail());
        userEmploymentEntity.setEmployerName(userSignUpDTO.getEmployerName());
        userEmploymentEntity.setEmploymentStartDate(userSignUpDTO.getEmploymentStartDate());
        userEmploymentEntity.setAnnualSalary(userSignUpDTO.getAnnualSalary());
        userEmploymentEntity.setPayFrequency(userSignUpDTO.getPayFrequency());
        userEmploymentRepository.save(userEmploymentEntity);
    }

    public boolean isEmailExists(String email) throws Exception {
        if (userAccountsRepository.findByEmail(email) == null) {
            throw new Exception("User already exists");
        }
        return false;
    }
}
