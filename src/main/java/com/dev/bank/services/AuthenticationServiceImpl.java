package com.dev.bank.services;

import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import com.dev.bank.services.client.AuthenticationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static final Pattern EMAIL_RE = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_RE = Pattern.compile("^\\+?[0-9]{7,15}$");

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        System.out.println("[AUTH][LOGIN][SERVICE] start: " + (request != null ? request.getUsername() : null));

        AuthLoginResponse response = new AuthLoginResponse();

        final String username = request != null ? request.getUsername() : null;
        final String password = request != null ? request.getPassword() : null;

        if (isBlank(username)) {
            response.setSuccess(false);
            response.setMessage("username is required");
            System.out.println("[AUTH][LOGIN][SERVICE] fail: username empty");
            return response;
        }
        if (isBlank(password)) {
            response.setSuccess(false);
            response.setMessage("password is required");
            System.out.println("[AUTH][LOGIN][SERVICE] fail: password empty");
            return response;
        }


        response.setSuccess(true);
        response.setMessage("User has been logged in successfully");
        response.setToken("demo-token-123");

        System.out.println("[AUTH][LOGIN][SERVICE] ok: " + username);
        return response;
    }

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        System.out.println("[AUTH][REGISTER][SERVICE] start: " + (request != null ? request.getUsername() : null));
        AuthRegisterResponse response = new AuthRegisterResponse();

        final String username = request != null ? request.getUsername() : null;
        final String password = request != null ? request.getPassword() : null;
        final String email    = request != null ? request.getEmail()    : null;
        final String birthday = request != null ? request.getBirthday() : null;
        final String phone    = request != null ? request.getPhoneNumber() : null;


        if (isBlank(username)) {
            response.setSuccess(false);
            response.setMessage("username is required");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: username empty");
            return response;
        }
        if (isBlank(password)) {
            response.setSuccess(false);
            response.setMessage("password is required");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: password empty");
            return response;
        }
        if (isBlank(email)) {
            response.setSuccess(false);
            response.setMessage("email is required");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: email empty");
            return response;
        }
        if (isBlank(birthday)) {
            response.setSuccess(false);
            response.setMessage("birthday is required (use yyyy-MM-dd)");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: birthday empty");
            return response;
        }


        if (!EMAIL_RE.matcher(email).matches()) {
            response.setSuccess(false);
            response.setMessage("email is invalid");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: email invalid -> " + email);
            return response;
        }


        try {
            LocalDate b = LocalDate.parse(birthday);
            if (b.isAfter(LocalDate.now())) {
                response.setSuccess(false);
                response.setMessage("birthday cannot be in the future");
                System.out.println("[AUTH][REGISTER][SERVICE] fail: birthday in future -> " + birthday);
                return response;
            }
        } catch (DateTimeParseException ex) {
            response.setSuccess(false);
            response.setMessage("birthday format must be yyyy-MM-dd");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: birthday parse -> " + birthday);
            return response;
        }


        if (!isBlank(phone) && !PHONE_RE.matcher(phone).matches()) {
            response.setSuccess(false);
            response.setMessage("phoneNumber is invalid");
            System.out.println("[AUTH][REGISTER][SERVICE] fail: phone invalid -> " + phone);
            return response;
        }


        response.setSuccess(true);
        response.setMessage("User has been registered successfully");

        System.out.println("[AUTH][REGISTER][SERVICE] ok: " + username);
        return response;
    }
}
