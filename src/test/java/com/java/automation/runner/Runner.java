package com.java.automation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:",
        glue = {"com.java.automation.glue", "com.java.automation.configuration"},
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        },
        monochrome = true
)

public class Runner {

}