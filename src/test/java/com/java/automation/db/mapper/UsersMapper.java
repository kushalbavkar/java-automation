package com.java.automation.db.mapper;


import com.java.automation.db.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Users(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("userid")
        );
    }

    public void dummy() {
        System.out.println("In User Mapper");
    }
}
