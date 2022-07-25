package com.ecommerce.hans.core.api;

import com.ecommerce.hans.common.config.DatabaseConfig;
import com.ecommerce.hans.common.support.DataRestQueryProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@EnableCaching
@SpringBootApplication()
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.ecommerce.hans.core.api", "com.ecommerce.hans.common.dao.impl"})
@Import({DataRestQueryProvider.class, DatabaseConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
