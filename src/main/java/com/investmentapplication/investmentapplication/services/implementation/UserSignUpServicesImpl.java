package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import com.investmentapplication.investmentapplication.exception.UserAlreadyExistsException;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.repository.UserEmploymentRepository;
import com.investmentapplication.investmentapplication.services.UserSignUpServices;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Service
public class UserSignUpServicesImpl implements UserSignUpServices {

    @Autowired
    private UserAccountsRepository userAccountsRepository;

    @Autowired
    private UserEmploymentRepository userEmploymentRepository;


    @Override
    public TokenResponse addUser(UserSignUpDTO userSignUpDTO) {
        TokenResponse response = new TokenResponse();
        try {
            // Check if email already exists
            String emailId = userSignUpDTO.getEmail();
            if (emailId != null) {
                List<UserAccountsEntity> userAccountDetails = userAccountsRepository.findByEmail(emailId);

                if (userAccountDetails.size()>0) {
                    throw new UserAlreadyExistsException("User email already exists!");
                } else {
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

                    response.setMessage("User Registered Successfully");
                }
            }
        }
        catch (UserAlreadyExistsException e) {
                response.setErrorCode(ErrorCode.USER_ALREADY_EXISTS);//throw user not found error
                response.setMessage(e.getMessage());
            }


        return response;
    }

}
