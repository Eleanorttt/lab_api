package com.dev.bank.services.client;
import com.dev.bank.models.auth.TokenData;

public interface TokenService {
    String gen(String user);
    boolean valid(String t);
    void kill(String t);
    TokenData data(String t);
}
