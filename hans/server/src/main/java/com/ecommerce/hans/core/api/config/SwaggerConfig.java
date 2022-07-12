package com.ecommerce.hans.core.api.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"!prod"})
@EnableSwagger2
@Configuration
public class SwaggerConfig {
}
