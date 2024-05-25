package com.revature.integration.repository2database;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class IntegrationTestDaoUser {
    public UserDao userDao;
    public Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
//        connection = ConnectionUtil.createConnection();
        userDao = new UserDao();
    }

    @Test
    public void testGetUserByUsernamePositive() throws SQLException {
        User u = new User();
        u.setId(0);
        u.setUsername("test");
        u.setPassword("test");

        User userReturn = userDao.getUserByUsername(u.getUsername());
        Assertions.assertEquals(u.getUsername(), userReturn.getUsername());
        Assertions.assertEquals(u.getPassword(), userReturn.getPassword());
        Assertions.assertTrue(userReturn.getId()>0);
    }
    @Test
    public void testGetUserByUsernameNegativeNoUser() throws SQLException {
        User u = new User();
        u.setId(0);
        u.setUsername("testGetUserByUsernameNegative");
        u.setPassword("valid");

        User userReturn = userDao.getUserByUsername(u.getUsername());
        Assertions.assertNull(userReturn);
    }
    @Test
    public void testRegisterUserPositive() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000,9999);
        User u = new User();
        u.setId(0);
        u.setUsername("IntegrationTestUser"+randomNum);
        u.setPassword("valid");

        UsernamePasswordAuthentication uauth = new UsernamePasswordAuthentication();
        uauth.setUsername(u.getUsername());
        uauth.setPassword(u.getPassword());
        User expectedUser = new User();
        User actualUser = userDao.createUser(uauth);
        Assertions.assertEquals(expectedUser, actualUser);
//        Assertions.assertEquals(u.getUsername(), actualUser.getUsername());
//        Assertions.assertTrue(actualUser.getId()>0);

    }
    @Test
    public void testRegisterUserNegativeUserExisted() {
        User u = new User();
        u.setId(0);
        u.setUsername("test");
        u.setPassword("valid");

        UsernamePasswordAuthentication uauth = new UsernamePasswordAuthentication();
        uauth.setUsername(u.getUsername());
        uauth.setPassword(u.getPassword());
        User expectedUser = new User();
        User actualUser = userDao.createUser(uauth);
        Assertions.assertEquals(expectedUser, actualUser);
//        Assertions.assertEquals(u.getUsername(), actualUser.getUsername());
//        Assertions.assertTrue(actualUser.getId()>0);

    }
}
