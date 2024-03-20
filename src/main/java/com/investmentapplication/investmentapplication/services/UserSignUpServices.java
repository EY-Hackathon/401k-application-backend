package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;


public interface UserSignUpServices {

    UserAccountsEntity addUser(UserSignUpDTO userSignUpDTO);

    boolean isEmailexists(String email) throws Exception;
}
