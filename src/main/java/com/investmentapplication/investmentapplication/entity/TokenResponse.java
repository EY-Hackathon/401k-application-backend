package com.investmentapplication.investmentapplication.entity;


import lombok.Data;

@Data
public class TokenResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
