package com.java.automation.configuration;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


/**
 * To enable DI for this project
 */

@CucumberContextConfiguration
@ContextConfiguration
@EnableAutoConfiguration
@ComponentScan("com.java.automation")
@TestPropertySource("classpath:application.properties")
public class CucumberSpringConfig {
}
