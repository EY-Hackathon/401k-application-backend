package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.UserEntity;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.implementation.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // User already exists
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);

        userRepository.save(newUser);
        return true;
    }
}
