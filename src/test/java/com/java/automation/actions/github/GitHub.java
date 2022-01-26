package com.java.automation.actions.github;

import com.java.automation.actions.Base;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile({"dev", "prod"})
public class GitHub extends Base {
    private static final Logger log = LoggerFactory.getLogger(GitHub.class);

    private String user = null;
    private String project = null;

    public GitHub home() {
        log.info("On GitHub home page");
        return this;
    }

    public GitHub navigateTo(final String url) {
        log.info("Navigating to URL [{}]", url);
        navigate(url);
        return this;
    }

    public GitHub search(final String user) {
        this.user = user;
        log.info("Searching for user [{}] on homepage", this.user);
        sendKeys(GitHubLocators.SEARCH_GITHUB, this.user);
        sendKeys(GitHubLocators.SEARCH_GITHUB, Keys.ENTER);
        return this;
    }

    public GitHub selectUser() {
        log.info("Selecting user [{}] from search window", this.user);
        click(GitHubLocators.NAV_USERS);
        wait(GitHubLocators.NAV_USERS_SELECTED, 10);
        click(GitHubLocators.format(GitHubLocators.USER, this.user));
        return this;
    }

    public GitHub searchProject(final String project) {
        this.project = project;
        log.info("Searching for project [{}] in user profile", this.project);
        sendKeys(GitHubLocators.SEARCH_PROJECT, this.project);
        return this;
    }

    public GitHub selectProject() {
        log.info("Selecting project [{}] from user profile", this.project);
        click(GitHubLocators.format(GitHubLocators.PROJECT, this.project));
        return this;
    }

    public Entities.ProjectDetails getProjectDetails() {
        String author = findElement(GitHubLocators.AUTHOR).getText();
        String project = findElement(GitHubLocators.REPOSITORY).getText();
        log.info("Project details: [{}/{}]", this.user, this.project);
        return new Entities.ProjectDetails(author, project);
    }
}

