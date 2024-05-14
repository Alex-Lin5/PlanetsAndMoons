package com.revature.unittest.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestUserService {
//    public UserService service;
    @Mock
    public UserDao dao;
    @InjectMocks
    public UserService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserRegistrationSuccess(){
        User u = new User();
        u.setId(0);
        u.setUsername("valid");
        u.setPassword("valid");

        User uret = new User();
        u.setId(10);
        u.setUsername("valid");
        u.setPassword("valid");

        UsernamePasswordAuthentication userauth = new UsernamePasswordAuthentication();
        userauth.setUsername(u.getUsername());
        userauth.setPassword(u.getPassword());

        Mockito.when(this.dao.getUserByUsername(u.getUsername())).thenReturn(null);
        Mockito.when(this.dao.createUser(userauth)).thenReturn(uret);
        User res = this.service.register(u);
//        Assertions.assertTrue(res.getId()>0);
        res.setId(0);
//        System.out.println(u + " " + res);
        Assertions.assertEquals(u.getUsername(), res.getUsername());
        Assertions.assertEquals(u.getPassword(), res.getPassword());
    }

    @Test
    public void testUserAuthenticateSuccess(){
        User u = new User();
        u.setId(0);
        u.setUsername("valid");
        u.setPassword("valid");

        User uret = new User();
        u.setId(10);
        u.setUsername("valid");
        u.setPassword("valid");

        UsernamePasswordAuthentication userauth = new UsernamePasswordAuthentication();
        userauth.setUsername(u.getUsername());
        userauth.setPassword(u.getPassword());

        Mockito.when(this.dao.getUserByUsername(u.getUsername())).thenReturn(uret);

        User res = this.service.authenticate(userauth);
        Assertions.assertEquals(u.getUsername(), res.getUsername());
        Assertions.assertEquals(u.getPassword(), res.getPassword());
    }
}
