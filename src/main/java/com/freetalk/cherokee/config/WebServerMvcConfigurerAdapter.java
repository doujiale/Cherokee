package com.freetalk.cherokee.config;

import com.freetalk.cherokee.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServerMvcConfigurerAdapter implements WebMvcConfigurer {

    @Value("${cherokee.gateway.server.token.enable:true}")
    private boolean checkServerToken;

    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor(checkServerToken);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }

}
