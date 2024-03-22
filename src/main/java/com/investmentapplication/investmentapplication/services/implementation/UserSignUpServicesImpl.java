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
        userAccountsEntity.setFirstname(userSignUpDTO.getFirstname());
        userAccountsEntity.setLastname(userSignUpDTO.getLastname());
        userAccountsEntity.setDateofbirth(userSignUpDTO.getDateofbirth());
        userAccountsEntity.setSsn(userSignUpDTO.getSsn());
        userAccountsEntity.setMailingaddress(userSignUpDTO.getMailingaddress());
        userAccountsEntity.setEmail(userSignUpDTO.getEmail());
        userAccountsEntity.setPhonenumber(userSignUpDTO.getPhonenumber());
        userAccountsEntity.setPassword(userSignUpDTO.getPassword());
        userAccountsRepository.save(userAccountsEntity);

        UserEmploymentEntity userEmploymentEntity = new UserEmploymentEntity();
        userEmploymentEntity.setEmail(userSignUpDTO.getEmail());
        userEmploymentEntity.setEmploymentname(userSignUpDTO.getEmploymentName());
        userEmploymentEntity.setEmploymentstartdate(userSignUpDTO.getEmploymentstartdate());
        userEmploymentEntity.setAnnualsalary(userSignUpDTO.getAnnualsalary());
        userEmploymentEntity.setPayfrequency(userSignUpDTO.getPayfrequency());
        userEmploymentRepository.save(userEmploymentEntity);
    }

    public boolean isEmailexists(String email) throws Exception {


        if (userAccountsRepository.findByEmail(email) == null) {
            throw new Exception("User already exists");
        }
        return false;
    }
}
