package com.dev.bank.services;

import com.dev.bank.models.auth.TokenData;
import com.dev.bank.services.client.TokenService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceImplTest {
    private final TokenService svc = new TokenServiceImpl();

    @Test
    void gen_valid_kill(){
        String t = svc.gen("ivan");
        assertNotNull(t);
        assertTrue(svc.valid(t));
        TokenData d = svc.data(t);
        assertEquals("ivan", d.user);
        assertTrue(d.exp > d.iat);
        svc.kill(t);
        assertFalse(svc.valid(t));
    }
}
