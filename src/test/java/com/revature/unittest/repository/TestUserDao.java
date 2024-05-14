package com.revature.unittest.repository;

import com.revature.models.User;
import com.revature.repository.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDao {
    @Mock
    public PreparedStatement ps;
    @InjectMocks
    public UserDao dao;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByUsername() throws SQLException {
        User u = new User();
        u.setId(0);
        u.setUsername("valid");
        u.setPassword("valid");

//        Mockito.when(this.ps.executeQuery()).thenReturn((ResultSet) u);

        User res = this.dao.getUserByUsername(u.getUsername());
        Assertions.assertEquals(u.getUsername(), res.getUsername());
        Assertions.assertEquals(u.getPassword(), res.getPassword());
        Assertions.assertTrue(res.getId()>0);
    }
}
