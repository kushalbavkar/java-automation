package com.java.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:",
        glue = {"com.java.automation.glue", "com.java.automation.configuration"},
        plugin = {
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
        },
        monochrome = true
)

public class Runner {

}