package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.TokenResponse;

public interface UserService {
    TokenResponse loginService(UserEntity request);
}
