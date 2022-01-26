package com.java.automation.db.dao;

import com.java.automation.db.mapper.UsersMapper;
import com.java.automation.db.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "DBUsersDao")
public class UsersDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> findAll() {
        final String query = "SELECT * FROM USERS";
        return jdbcTemplate.query(query, new UsersMapper());
    }

    public Users findByFirstName(final String firstName) {
        final String query = "SELECT * FROM USERS where FIRSTNAME=?";
        return jdbcTemplate.queryForObject(query, new UsersMapper(), firstName);
    }
}
