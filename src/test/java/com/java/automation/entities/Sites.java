package com.java.automation.entities;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum Sites {
    GITHUB("GitHub", "https://github.com"),
    GITLAB("GitLab", "https://gitlab.com"),
    GOOGLE("Google", "https://www.google.com");

    private String name;
    private String url;

    Sites(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static Optional<Sites> parse(String text) {
        return Arrays.stream(values())
                .filter(site -> site.name.equalsIgnoreCase(text))
                .findFirst();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}