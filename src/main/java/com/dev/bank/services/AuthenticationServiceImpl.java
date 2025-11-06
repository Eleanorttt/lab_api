package com.dev.bank.services;

import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import com.dev.bank.services.client.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        AuthLoginResponse response = new AuthLoginResponse();

        final String username = request.getUsername();
        if (username == null || username.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Username field is required.");

            return response; //method stops working here!
        }

        final String password = request.getPassword();
        if (password == null || password.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Password field is required");

            return response;
        }
        if (password.length() < 5) {
            response.setSuccess(false);
            response.setMessage("Entered password is too short");

            return response;
        }

        response.setSuccess(true);
        response.setMessage("User has been logged in successfully");
        response.setToken("2132132132");

        return response;
    }

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        return null;
    }
}
