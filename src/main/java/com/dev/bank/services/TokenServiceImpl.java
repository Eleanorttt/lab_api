package com.dev.bank.services;

import com.dev.bank.models.auth.TokenData;
import com.dev.bank.services.client.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String APP = "LabBank";
    private static final long TTL = 3600;
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();
    private final ObjectMapper om = new ObjectMapper();

    private String enc(Map<String,Object> m){
        try { return Base64.getUrlEncoder().withoutPadding().encodeToString(om.writeValueAsBytes(m)); }
        catch(Exception e){ return null; }
    }
    private Map<String,Object> dec(String t) throws Exception {
        return om.readValue(Base64.getUrlDecoder().decode(t), new TypeReference<Map<String,Object>>(){});
    }

    @Override public String gen(String user){
        long now = Instant.now().getEpochSecond();
        Map<String,Object> m = new HashMap<>();
        m.put("app", APP); m.put("user", user); m.put("role", "user");
        m.put("iat", now); m.put("exp", now + TTL); m.put("id", UUID.randomUUID().toString());
        return enc(m);
    }
    @Override public boolean valid(String t){
        if(t==null || blacklist.contains(t)) return false;
        try{
            Map<String,Object> m = dec(t);
            long exp = ((Number)m.get("exp")).longValue();
            return APP.equals(m.get("app")) && Instant.now().getEpochSecond() <= exp;
        }catch(Exception e){ return false; }
    }
    @Override public void kill(String t){ if(t!=null) blacklist.add(t); }
    @Override public TokenData data(String t){
        try{
            Map<String,Object> m = dec(t);
            TokenData d = new TokenData();
            d.app=(String)m.get("app"); d.user=(String)m.get("user"); d.role=(String)m.get("role");
            d.iat=((Number)m.get("iat")).longValue(); d.exp=((Number)m.get("exp")).longValue(); d.id=(String)m.get("id");
            return d;
        }catch(Exception e){ return null; }
    }
}
