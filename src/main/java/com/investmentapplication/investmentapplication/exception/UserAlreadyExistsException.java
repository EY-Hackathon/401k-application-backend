package com.investmentapplication.investmentapplication.exception;

import com.investmentapplication.investmentapplication.util.ErrorCode;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException(String message) {

        super(message);
    }

    public UserAlreadyExistsException(ErrorCode errorCode, String s) {
    }

}
