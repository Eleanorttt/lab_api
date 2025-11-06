package com.dev.bank.models.response;

public class AuthLoginResponse extends BaseResponse {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

//success;
//message;
//token;
