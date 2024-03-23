package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.exception.InvalidCredentialsException;
import com.investmentapplication.investmentapplication.exception.UserNotFoundException;
import com.investmentapplication.investmentapplication.filter.JwtUtil;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.services.UserService;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAccountsRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public TokenResponse loginService(UserAccountsEntity request) {
        TokenResponse response = new TokenResponse();
        try {
            List<UserAccountsEntity> storedUser = userRepository.findByEmail(request.getEmail()); //find user by email
                if (storedUser.isEmpty()) {
                    throw new UserNotFoundException("User email does not exist");
                }
                storedUser.forEach(user -> {
                if (!request.getPassword().equals(user.getPassword())) {
                    throw new InvalidCredentialsException("Incorrect password");
                }

                String token = JwtUtil.generateToken(user); //generate jwt token when user logs in
                response.setToken(token);
            });
        } catch (UserNotFoundException e) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND); //throw user not found error
            response.setMessage(e.getMessage());
        } catch (InvalidCredentialsException e) { //throw invalid credentials if credentials don't match
            response.setErrorCode(ErrorCode.INVALID_CREDENTIALS);
            response.setMessage(e.getMessage());
        } catch (Exception e) { //throw authentication failed error if the user or password doesn't match
            response.setErrorCode(ErrorCode.AUTHENTICATION_ERROR);
            response.setMessage("Authentication failed");
        }

        return response;
    }

}