package com.java.automation.actions.github;

import org.openqa.selenium.By;

class GitHubLocators {
    static final By SEARCH_GITHUB = By.xpath("//input[@placeholder='Search GitHub']");
    static final By NAV_USERS = By.xpath("//nav/a[text()='Users']");
    static final By NAV_USERS_SELECTED = By.xpath("//a[contains(@class, 'selected') and text()='Users']");
    static final By SEARCH_PROJECT = By.id("your-repos-filter");
    static final By AUTHOR = By.xpath("//h1/descendant::a[@rel='author']");
    static final By REPOSITORY = By.xpath("//h1/strong/a");

    static final String USER = "//a[contains(text(),'%s')]";
    static final String PROJECT = "//a[contains(text(),'%s')]";

    static By format(String locator, String text) {
        return By.xpath(String.format(locator, text));
    }
}
