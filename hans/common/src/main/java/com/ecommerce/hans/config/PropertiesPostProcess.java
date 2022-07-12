package com.ecommerce.hans.config;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesPostProcess implements EnvironmentPostProcessor {

    private StandardPBEStringEncryptor stdStringEncryptor;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        final String key = environment.getProperty("jasypt.encryptor.secret");

        final Properties props = new Properties();

        environment.getPropertySources()
                .forEach(p -> {
                    if (p instanceof MapPropertySource) {
                        if (p.getName().startsWith("applicationConfig")) {

                            MapPropertySource mp = (MapPropertySource) p;

                            for (String name : mp.getPropertyNames()) {
                                if (name.contains("password") && StringUtils.isNotBlank(String.valueOf(mp.getProperty(name)))) {

                                    if (props.containsKey(name)) {
                                        continue;
                                    }

                                    props.put(name, stdStringEncryptor(key).decrypt(
                                            String.valueOf(mp.getProperty(name))));
                                }
                            }
                        }
                    } else if (p instanceof SimpleCommandLinePropertySource) {
                        SimpleCommandLinePropertySource source = (SimpleCommandLinePropertySource) p;

                        for (String name : source.getPropertyNames()) {
                            if (name.contains("password") && StringUtils.isNotBlank(String.valueOf(source.getProperty(name)))) {

                                if (props.containsKey(name)) {
                                    continue;
                                }

                                props.put(name, stdStringEncryptor(key).decrypt(
                                        String.valueOf(source.getProperty(name))));
                            }
                        }
                    }
                });

        environment.getPropertySources().addFirst(new PropertiesPropertySource("decoded", props));
    }

    private StandardPBEStringEncryptor stdStringEncryptor(String secret) {

        if (stdStringEncryptor != null) {
            return stdStringEncryptor;
        }

        stdStringEncryptor = new StandardPBEStringEncryptor();
        stdStringEncryptor.setPassword(
                new String(DatatypeConverter.parseBase64Binary(secret), StandardCharsets.UTF_8));

        return stdStringEncryptor;
    }
}
