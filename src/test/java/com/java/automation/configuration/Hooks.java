package com.java.automation.configuration;

import com.java.automation.webdriver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    private DriverManager manager;

    @Autowired
    public Hooks(DriverManager manager) {
        this.manager = manager;
    }

    @Before
    public void init(final Scenario scenario) {
        log.info("Staring test execution!");
    }

    @After
    public void teardown(final Scenario scenario) {
        manager.quitDriver();
        log.info("Test execution complete!");
    }

}
