package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.dto.UserSignUpDTO;
import com.investmentapplication.investmentapplication.entity.TokenResponse;


public interface UserSignUpServices {

    TokenResponse addUser(UserSignUpDTO userSignUpDTO);

}
