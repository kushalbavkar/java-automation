package com.java.automation.entities;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class TestContext {
    private String author;
    private String project;
    private Sites site;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    public Sites getSite() {
        return site;
    }

    public void setSite(final Sites site) {
        this.site = site;
    }
}
