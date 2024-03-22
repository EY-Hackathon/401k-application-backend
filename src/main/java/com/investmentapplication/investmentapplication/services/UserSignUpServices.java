package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;


public interface UserSignUpServices {

    public void addUser(UserSignUpDTO userSignUpDTO);

    boolean isEmailexists(String email) throws Exception;
}
