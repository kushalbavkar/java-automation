package com.java.automation.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active:dev}.properties")
public class ConfigReader {
    private final static Logger log = LoggerFactory.getLogger(ConfigReader.class);
    private static final String PROFILE = "spring.profiles.active";

    @Bean(name = "props")
    public Properties loadProperties() throws IOException {
        log.info("Loading properties from [application.properties]");
        final PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        final String envFile = getEnvironmentFileName(pfb);
        pfb.setLocations(new ClassPathResource("/application.properties"), new ClassPathResource(envFile));
        pfb.afterPropertiesSet();
        return pfb.getObject();
    }

    private String getEnvironmentFileName(final PropertiesFactoryBean pfb) throws IOException {
        pfb.setLocation(new ClassPathResource("/application.properties"));
        pfb.afterPropertiesSet();
        final String env = Objects.requireNonNull(pfb.getObject()).getProperty(PROFILE);
        String profileProps = "/application-" + env + ".properties";
        log.info("Loading properties from profile properties [{}]", profileProps);
        return profileProps;
    }
}
