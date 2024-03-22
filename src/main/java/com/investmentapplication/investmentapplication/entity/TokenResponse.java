package com.investmentapplication.investmentapplication.entity;


import com.investmentapplication.investmentapplication.util.ErrorCode;
import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String message;
    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}