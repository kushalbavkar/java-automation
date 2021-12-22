Feature: Check GitHub projects

  As as SDET working on python project
  I want to search for pytest-bdd project
  So that I can refer & use it for test automation

  Scenario:  Verify presence of pytest-bdd project
    Given I am on "GitHub" homepage
    When I search for "pytest-dev" user
    And I select "pytest-bdd" project
    Then I should see the repository page
