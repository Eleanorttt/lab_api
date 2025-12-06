package com.dev.bank.security;

import com.dev.bank.models.auth.TokenData;
import com.dev.bank.services.client.TokenService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenInterceptor implements HandlerInterceptor {
    private final TokenService tokens;
    public TokenInterceptor(TokenService tokens){
        this.tokens = tokens;
    }

    private boolean need(Object h){
        if(!(h instanceof HandlerMethod)) return false;
        HandlerMethod m = (HandlerMethod) h;
        return m.hasMethodAnnotation(RequireToken.class)
                || m.getBeanType().isAnnotationPresent(RequireToken.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object h) throws Exception {
        if(!need(h)) return true;
        String auth = req.getHeader("Authorization");
        String t = (auth!=null && auth.startsWith("Bearer ")) ? auth.substring(7) : req.getHeader("X-Auth-Token");
        if(t==null || !tokens.valid(t)){
            res.setStatus(401);
            res.getWriter().write("Треба валідний токен");
            return false;
        }
        TokenData d = tokens.data(t);
        req.setAttribute("token", t);
        req.setAttribute("tokenData", d);
        return true;
    }
}
