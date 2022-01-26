package com.java.automation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBUtils {
    private JdbcTemplate template;

    @Autowired
    public DBUtils(final JdbcTemplate template) {
        this.template = template;
    }

    public <T> List<T> getResult(final String query, final RowMapper<T> mapper, final String... args) {
        return template.query(query, mapper, Arrays.stream(args).toArray());
    }
}
