package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.exception.UserAlreadyExistsException;
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

    // This method adds a new user based on the information provided in the DTO
    @Override
    public void addUser(UserSignUpDTO userSignUpDTO) throws Exception {
        // Check if email already exists
        if (isEmailExists(userSignUpDTO.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        // Creating a new UserAccountsEntity and setting its properties based on the DTO
        UserAccountsEntity userAccountsEntity = new UserAccountsEntity();
        userAccountsEntity.setFirstName(userSignUpDTO.getFirstName());
        userAccountsEntity.setLastName(userSignUpDTO.getLastName());
        userAccountsEntity.setDateOfBirth(userSignUpDTO.getDateOfBirth());
        userAccountsEntity.setSsn(userSignUpDTO.getSsn());
        userAccountsEntity.setMailingAddress(userSignUpDTO.getMailingAddress());
        userAccountsEntity.setEmail(userSignUpDTO.getEmail());
        userAccountsEntity.setPhoneNumber(userSignUpDTO.getPhoneNumber());
        userAccountsEntity.setPassword(userSignUpDTO.getPassword());

        // Saving the user account entity to the repository
        userAccountsRepository.save(userAccountsEntity);

        // Creating a new UserEmploymentEntity and setting its properties based on the DTO
        UserEmploymentEntity userEmploymentEntity = new UserEmploymentEntity();
        userEmploymentEntity.setEmail(userSignUpDTO.getEmail());
        userEmploymentEntity.setEmployerName(userSignUpDTO.getEmployerName());
        userEmploymentEntity.setEmploymentStartDate(userSignUpDTO.getEmploymentStartDate());
        userEmploymentEntity.setAnnualSalary(userSignUpDTO.getAnnualSalary());
        userEmploymentEntity.setPayFrequency(userSignUpDTO.getPayFrequency());

        // Saving the user employment entity to the repository
        userEmploymentRepository.save(userEmploymentEntity);
    }

    // This method checks if an email already exists in the user accounts repository
    public boolean isEmailExists(String email) throws Exception {
        // If user with the provided email exists, throw an exception
        if (userAccountsRepository.findByEmail(email) != null) {
            throw new Exception("User already exists");
        }
        // Otherwise, return false indicating email does not exist
        return false;
    }
}
