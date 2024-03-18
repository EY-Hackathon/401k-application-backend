package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserEntity;

public interface UserService {
    TokenResponse loginService(UserEntity request);
}
