package com.java.automation.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Profile("prod")
public class ProdDriverManager implements DriverManager {
    private final static Logger log = LoggerFactory.getLogger(ProdDriverManager.class);

    @Autowired
    private Properties props;

    private WebDriver driver = null;

    @Override
    public WebDriver getDriver() {
        if (driver != null)
            return driver;

        try {
            URL url = new URL(props.getProperty("driver"));
            log.info("Starting webdriver using remote driver. URL [{}]", url);

            ChromeOptions options = getOptions();
            log.info("Using ChromeOptions [{}]", options.toJson());

            driver = new RemoteWebDriver(url, options);
            driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            log.error("Malformed driver URL", e);
        }

        return driver;
    }

    private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.merge(defaultCapabilities());

        return options;
    }

    private DesiredCapabilities defaultCapabilities() {
        Map<String, Object> moonOptions = new HashMap<>();
        moonOptions.put("enableVNC", true);
        moonOptions.put("enableVideo", false);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("moon:options", moonOptions);

        return capabilities;
    }

    @Override
    public void quitDriver() {
        log.info("Destroying driver instance");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
