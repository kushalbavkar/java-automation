# Java BDD framework

* Testing framework for black box testing using io.cucumber, java, selenium

## Pre-requisites

1. Java 1.8 or greater
2. Gradle
3. IDE of your choice (IntelliJ Idea / Pycharm / VS Code etc...)
4. Chromedriver binary
5. Git

## Setup Project

### Navigate to project root

    $ cd {project.dir}

### Build project

    $ gradlew.bat build -D skipTests

## Running tests

### Configuration

The tests are configured to run on two environments

1. `dev`:  (denotes local)

   Configure the chrome driver binary location in `{user.dir}/src/test/resouces/binaries/chromedriver.exe`
   or you can set your custom path in `application-dev.properties`

2. `prod`: (denotes prod like setup)

   This implementation is planned to run tests on remote webdriver using moon.

The tests environments are managed using spring profiles and can be configured using
`spring.profiles.active` property in `application.properties`.

### Run tests

Simply execute grade task

    $ gradlew.bat cucumber
