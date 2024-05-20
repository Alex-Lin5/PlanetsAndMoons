package com.revature.unit.repository;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.utilities.ConnectionUtil;
import io.javalin.Javalin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUserDao {
//    @Mock
//    public PreparedStatement ps;
//    @InjectMocks
    public UserDao dao;

    public Connection connection;

    @BeforeEach
    public void setup() throws SQLException{
//        connection = ConnectionUtil.createConnection();
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByUsernamePositive() throws SQLException {
        User u = new User();
        u.setId(0);
        u.setUsername("testGetUserByUsername");
        u.setPassword("valid");

//        Mockito.when(this.ps.executeQuery()).thenReturn((ResultSet) u);

        User res = this.dao.getUserByUsername(u.getUsername());
        Assertions.assertEquals(u.getUsername(), res.getUsername());
        Assertions.assertEquals(u.getPassword(), res.getPassword());
        Assertions.assertTrue(res.getId()>0);
    }
    @Test
    public void testRegisterUserPositive() {
        User u = new User();
        u.setId(0);
        u.setUsername("testUserDaoPositive1");
        u.setPassword("valid");

        UsernamePasswordAuthentication uauth = new UsernamePasswordAuthentication();
        uauth.setUsername(u.getUsername());
        uauth.setPassword(u.getPassword());
        UserDao userDao = new UserDao();
        User expectedUser = new User();
        User actualUser = userDao.createUser(uauth);
        Assertions.assertEquals(expectedUser, actualUser);

    }
}
