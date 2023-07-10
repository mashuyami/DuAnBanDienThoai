package com.spring;

import com.spring.interceptors.AdminInterceptor;
import com.spring.interceptors.GuestInterceptor;
import com.spring.interceptors.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    @Autowired
    private GuestInterceptor guestInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(guestInterceptor)
                .addPathPatterns("/user/**", "/admin/**", "/cart/checkout")
                .excludePathPatterns("/login", "/register", "/logout");

        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/login", "/register", "/admin/**");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/login", "/register");
    }

}
