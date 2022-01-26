package com.java.automation.units;

import com.java.automation.db.dao.UsersDao;
import com.java.automation.db.mapper.UsersMapper;
import com.java.automation.db.model.Users;
import com.java.automation.utils.DBUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.java.automation")
public class DBTest {
    @Autowired
    UsersDao usersDao;

    @Autowired
    DBUtils dbUtils;

    @Test
    public void daoTest() {
        System.out.println("DAO");
        List<Users> users = usersDao.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void utilsTest() throws InstantiationException, IllegalAccessException {
        System.out.println("DBUTILS");
        final String query = "SELECT * FROM USERS where FIRSTNAME=?";
        List<Users> users = dbUtils.getResult(query, new UsersMapper(), "Kushal");
        users.forEach(System.out::println);
    }
}
