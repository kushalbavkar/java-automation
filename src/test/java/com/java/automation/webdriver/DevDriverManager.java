package com.java.automation.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
@Profile("dev")
public class DevDriverManager implements DriverManager {
    private final static Logger log = LoggerFactory.getLogger(DevDriverManager.class);

    @Autowired
    private Properties props;

    private WebDriver driver = null;

    @Override
    public WebDriver getDriver() {
        if (driver != null)
            return driver;

        Path driverPath = findLocalDriver();
        System.setProperty("webdriver.chrome.driver", driverPath.toString());
        log.info("Starting webdriver using local driver. Path [{}]", driverPath.toString());

        ChromeOptions options = getOptions();
        log.info("Using ChromeOptions [{}]", options.toJson());

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

        return driver;
    }

    private Path findLocalDriver() {
        String driver = props.getProperty("driver");
        Path path = Paths.get(driver);
        return (Files.exists(path)) ?
                path :
                Paths.get(System.getProperty("user.dir")).resolve("src/test/resources/" + driver);
    }

    private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");

        if (this.props.getProperty("headless").equalsIgnoreCase("true"))
            options.addArguments("--headless");
        options.merge(defaultCapabilities());

        return options;
    }

    private DesiredCapabilities defaultCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("acceptInsecureCerts", true);

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
