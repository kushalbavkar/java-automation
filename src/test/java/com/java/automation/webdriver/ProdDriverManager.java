package com.java.automation.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
@Profile("prod")
public class ProdDriverManager implements DriverManager {
    private static final Logger log = LoggerFactory.getLogger(ProdDriverManager.class);

    private WebDriver driver = null;
    private Properties props;

    @Autowired
    public ProdDriverManager(final Properties props) {
        this.props = props;
    }

    @Override
    public WebDriver getDriver() {
        if (this.driver != null)
            return this.driver;

        try {
            URL url = new URL(this.props.getProperty("driver"));
            log.info("Starting webdriver using remote driver. URL [{}]", url);

            ChromeOptions options = getOptions();
            log.info("Using ChromeOptions [{}]", options.toJson());

            this.driver = new RemoteWebDriver(url, options);
            this.driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            this.driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
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
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }
}
