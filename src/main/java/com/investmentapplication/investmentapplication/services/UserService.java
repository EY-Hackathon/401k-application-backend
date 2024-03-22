package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;

public interface UserService {
    TokenResponse loginService(UserAccountsEntity request);
}
