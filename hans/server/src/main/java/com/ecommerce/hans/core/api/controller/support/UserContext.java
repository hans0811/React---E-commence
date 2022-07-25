package com.ecommerce.hans.core.api.controller.support;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

    private UserContext() {}

    public static String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
