package com.java.automation.configuration;

import com.java.automation.webdriver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    private final static Logger log = LoggerFactory.getLogger(Hooks.class);
    @Autowired
    DriverManager manager;

    @Before
    public void init(Scenario scenario) {
        log.info("Staring test execution!");
    }

    @After
    public void teardown(Scenario scenario) {
        manager.quitDriver();
        log.info("Test execution complete!");
    }

}
