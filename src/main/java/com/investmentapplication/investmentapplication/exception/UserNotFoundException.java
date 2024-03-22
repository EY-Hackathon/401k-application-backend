package com.investmentapplication.investmentapplication.exception;

import com.investmentapplication.investmentapplication.util.ErrorCode;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(ErrorCode errorCode, String s) {
    }
}