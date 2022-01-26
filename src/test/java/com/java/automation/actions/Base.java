package com.java.automation.actions;

import com.java.automation.webdriver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class Base {
    private WebDriver driver = null;

    private DriverManager manager;

    @Autowired
    public void setManager(final DriverManager manager) {
        this.manager = manager;
    }

    @PostConstruct
    private void setDriver() {
        this.driver = this.manager.getDriver();
    }

    public void navigate(final String url) {
        this.driver.get(url);
    }

    public WebElement findElement(final By locator) {
        return find(locator);
    }

    public void sendKeys(final By locator, final CharSequence... text) {
        find(locator).sendKeys(text);
    }

    public void click(final By locator) {
        find(locator).click();
    }

    public WebElement wait(final By locator, final int timeout) {
        final WebDriverWait wait = new WebDriverWait(this.driver, timeout);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement find(final By locator) {
        final WebDriverWait wait = new WebDriverWait(this.driver, 10);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
