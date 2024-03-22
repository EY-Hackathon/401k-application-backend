package com.investmentapplication.investmentapplication.exception;

import com.investmentapplication.investmentapplication.util.ErrorCode;

public class EmploymentDetailsException extends RuntimeException {
    public EmploymentDetailsException(String message) {
        super(message);
    }

    public EmploymentDetailsException(ErrorCode errorCode, String s) {
    }
}