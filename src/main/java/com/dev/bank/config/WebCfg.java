package com.dev.bank.config;

import com.dev.bank.security.TokenInterceptor;
import com.dev.bank.services.client.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCfg implements WebMvcConfigurer {
    private final TokenService tokens;
    public WebCfg(TokenService tokens){ this.tokens = tokens; }

    @Bean
    public TokenInterceptor tokenInterceptor(){ return new TokenInterceptor(tokens); }

    @Override
    public void addInterceptors(InterceptorRegistry r){
        r.addInterceptor(tokenInterceptor());
    }
}
