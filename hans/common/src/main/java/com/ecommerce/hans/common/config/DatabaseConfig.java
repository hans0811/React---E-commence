package com.ecommerce.hans.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.ecommerce.hans.common.dao",
            entityManagerFactoryRef = "entityManagerFactory",
            transactionManagerRef = "transactionManager")
    public static class ElndbDatabaseConfiguration {

        @Bean
        @Primary
        @ConfigurationProperties("spring.datasource")
        public DataSourceProperties dataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        @Primary
        @ConfigurationProperties("spring.datasource.hikari")
        public DataSource dataSource() {
            return dataSourceProperties().initializeDataSourceBuilder()
                    .type(HikariDataSource.class).build();
        }

        @Bean
        @Primary
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
            return builder.dataSource(dataSource())
                    .packages("com.ecommerce.hans.common.entity")
                    .build();
        }

        @Bean
        @Primary
        public EntityManager entityManager(
                final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
            return entityManagerFactory.getObject().createEntityManager();
        }

        @Bean
        @Primary
        public PlatformTransactionManager transactionManager(
                final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory.getObject());
        }
    }
}
