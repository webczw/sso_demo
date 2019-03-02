package com.webczw.userssoclient;

import com.webczw.userssoclient.interceptor.SessionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Wilber
 */
@SpringBootApplication
public class UserssoclientApplication extends WebMvcConfigurationSupport {

public static void main(String[] args) {
        SpringApplication.run(UserssoclientApplication.class, args);
        }

@Override
protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/test/*");
        }

@Bean
public SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
        }
        }
