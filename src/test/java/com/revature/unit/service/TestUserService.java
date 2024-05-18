package com.revature.unit.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserService {
    @Mock
    public UserDao userDao;
    @InjectMocks
    public UserService userService;

//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void testUserRegistrationPositive() {
        UsernamePasswordAuthentication user = new UsernamePasswordAuthentication();
        user.setUsername("testUserRegistrationPositive");
        user.setPassword("validpwd");
        User createdUser = new User();
        createdUser.setId(1);
        createdUser.setUsername("testUserRegistrationPositive");
        createdUser.setPassword("validpwd");
        when(userDao.createUser(user)).thenReturn(createdUser);
        User actualUser = userService.register(createdUser);
        Mockito.verify(this.userDao, times(1)).createUser(user);
        Assertions.assertEquals(createdUser, actualUser);

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
//        userauth.setUsername(u.getUsername());
//        userauth.setPassword(u.getPassword());
        userauth.setUsername("validusr");
        userauth.setPassword("validpwd");

        when(this.userDao.getUserByUsername(u.getUsername())).thenReturn(uret);

        User res = this.userService.authenticate(userauth);
        Assertions.assertEquals(u.getUsername(), res.getUsername());
        Assertions.assertEquals(u.getPassword(), res.getPassword());
    }
}
