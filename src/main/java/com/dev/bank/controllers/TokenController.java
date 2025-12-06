package com.dev.bank.controllers;

import com.dev.bank.models.auth.TokenData;
import com.dev.bank.security.RequireToken;
import com.dev.bank.services.client.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class TokenController {
    @Autowired TokenService tokens;

    @PostMapping("/refresh")
    @RequireToken
    public Map<String,Object> refresh(HttpServletRequest req){
        TokenData d = (TokenData) req.getAttribute("tokenData");
        String oldT = (String) req.getAttribute("token");
        String newT = tokens.gen(d!=null?d.user:"user");
        tokens.kill(oldT);
        Map<String,Object> m = new HashMap<>();
        m.put("token", newT); m.put("msg","оновлено");
        return m;
    }

    @PostMapping("/invalidate")
    @RequireToken
    public Map<String,Object> invalidate(HttpServletRequest req){
        String t = (String) req.getAttribute("token");
        tokens.kill(t);
        Map<String,Object> m = new HashMap<>();
        m.put("ok", true); m.put("msg","інвалідовано");
        return m;
    }
}
