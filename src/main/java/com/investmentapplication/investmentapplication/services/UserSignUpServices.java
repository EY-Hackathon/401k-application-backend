package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;



public interface UserSignUpServices {

    void addUser(UserSignUpDTO userSignUpDTO);

    boolean isEmailExists(String email) throws Exception;
}
