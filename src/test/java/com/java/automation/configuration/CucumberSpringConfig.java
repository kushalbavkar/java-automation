package com.java.automation.configuration;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;


/**
 * To enable DI for this project
 */

@CucumberContextConfiguration
@EnableAutoConfiguration
@DirtiesContext
@ComponentScan("com.java.automation")
@TestPropertySource("classpath:application.properties")
public class CucumberSpringConfig {
}
