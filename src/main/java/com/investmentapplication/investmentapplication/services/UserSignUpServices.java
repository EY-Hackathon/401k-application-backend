package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;



public interface UserSignUpServices {

    void addUser(UserSignUpDTO userSignUpDTO) throws Exception;

    boolean isEmailExists(String email) throws Exception;
}
