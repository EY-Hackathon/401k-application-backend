package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.filter.JwtUtil;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Override
    public TokenResponse loginService(UserEntity request) {
        boolean passwordMatch = false;
        TokenResponse response = new TokenResponse();
        UserEntity storedUser = userRepository.findByUsername(request.getUsername());
            passwordMatch = request.getPassword().equals(storedUser.getPassword());
        if (passwordMatch) {
            response.setToken(jwtUtil.generateToken(storedUser));
        }
        return response;
    }
}
