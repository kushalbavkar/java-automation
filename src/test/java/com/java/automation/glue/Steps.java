package com.java.automation.glue;

import com.java.automation.actions.github.Entities;
import com.java.automation.actions.github.GitHub;
import com.java.automation.entities.Sites;
import com.java.automation.entities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Steps {
    @Autowired
    GitHub gitHub;

    @Autowired
    TestContext context;

    @Given("I am on {string} homepage")
    public void iAmOnHomepage(String site) {
        Sites.parse(site).ifPresent(s -> context.setSite(s));
        gitHub.navigateTo(context.getSite().getUrl());
    }

    @When("I search for {string} user")
    public void iSearchForUser(String user) {
        context.setAuthor(user);
        gitHub.home().search(context.getAuthor()).selectUser();
    }

    @And("I select {string} project")
    public void iSelectProject(String project) {
        context.setProject(project);
        gitHub.searchProject(context.getProject()).selectProject();
    }

    @Then("I should see the repository page")
    public void iShouldSeeTheRepositoryPage() {
        Entities.ProjectDetails project = gitHub.getProjectDetails();
        assertThat("Author name", context.getAuthor(), equalTo(project.getAuthor()));
        assertThat("Project name", context.getProject(), equalTo(project.getProject()));
    }
}
