package com.java.automation.actions.github;

public class Entities {
    public static class ProjectDetails {
        private String author;
        private String project;

        public ProjectDetails(final String author, final String project) {
            this.author = author;
            this.project = project;
        }

        public String getAuthor() {
            return author;
        }

        public String getProject() {
            return project;
        }
    }
}
