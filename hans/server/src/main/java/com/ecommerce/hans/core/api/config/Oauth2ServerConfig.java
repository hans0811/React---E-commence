package com.ecommerce.hans.core.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
public class Oauth2ServerConfig {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig{

    }




}
