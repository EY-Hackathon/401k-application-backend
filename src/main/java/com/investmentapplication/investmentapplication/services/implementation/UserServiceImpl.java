package com.investmentapplication.investmentapplication.services.implementation;
import com.investmentapplication.investmentapplication.entity.TokenResponse;
import com.investmentapplication.investmentapplication.exception.InvalidCredentialsException;
import com.investmentapplication.investmentapplication.exception.UserNotFoundException;
import com.investmentapplication.investmentapplication.filter.JwtUtil;
import com.investmentapplication.investmentapplication.repository.UserRepository;
import com.investmentapplication.investmentapplication.services.UserService;
import com.investmentapplication.investmentapplication.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public TokenResponse loginService(UserEntity request) {
        TokenResponse response = new TokenResponse();
        try {
            UserEntity storedUser = userRepository.findByEmail(request.getEmail()); //find user by email

            if (storedUser == null) {
                throw new UserNotFoundException("User email does not exist");
            }

            if (!request.getPassword().equals(storedUser.getPassword())) {
                throw new InvalidCredentialsException("Incorrect password");
            }

            String token = jwtUtil.generateToken(storedUser); //generate jwt token when user logs in
            response.setToken(token);
        } catch (UserNotFoundException e) {
            response.setErrorCode(ErrorCode.USER_NOT_FOUND);//throw user not found error
            response.setMessage(e.getMessage());
        } catch (InvalidCredentialsException e) { //throw invalid credentials if credentials dont match
            response.setErrorCode(ErrorCode.INVALID_CREDENTIALS);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.AUTHENTICATION_ERROR);
            response.setMessage("Authentication failed");
        }

        return response;
    }

}