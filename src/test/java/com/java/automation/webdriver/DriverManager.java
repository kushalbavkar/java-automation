package com.java.automation.webdriver;

import org.openqa.selenium.WebDriver;

public interface DriverManager {
    public WebDriver getDriver();

    public void quitDriver();
}
