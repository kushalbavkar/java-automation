package com.java.automation.excel.dao;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.java.automation.excel.ExcelManager;
import com.java.automation.excel.model.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ExcelUsersDao")
public class UsersDao {
    private static final String DATA_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\GitHub.xlsx";
    private ExcelManager utils;

    public UsersDao() throws FilloException {
        this.utils = new ExcelManager(DATA_FILE);
    }

    public List<Users> findAll() throws FilloException {
        final Recordset recordset = utils.Select("SELECT * FROM USERS");
        List<Users> usersList = new ArrayList<>();

        while (recordset.next()) {
            usersList.add(valuesFrom(recordset));
        }

        return usersList;
    }


    public Users findByFirstName(final String firstName) throws FilloException {
        final Recordset recordset = utils.Select("SELECT * FROM USERS where FIRSTNAME=?");
        Users users = null;

        if (recordset.next()) {
            users = valuesFrom(recordset);
        }

        return users;
    }

    private Users valuesFrom(final Recordset recordset) throws FilloException {
        Users user = new Users();
        user.setId(Integer.parseInt(recordset.getField("Id")));
        user.setFirstname(recordset.getField("FirstName"));
        user.setLastname(recordset.getField("LastName"));
        user.setUserid(recordset.getField("UserId"));

        return user;
    }
}
