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

import static org.mockito.Mockito.*;

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
        verify(this.userDao, times(1)).createUser(user);
        Assertions.assertEquals(createdUser, actualUser);

    }
    @Test
    public void testUserRegistrationNegativeLongCharacters() {
        User createdUser = new User();
        createdUser.setId(1);
        createdUser.setUsername("testUserRegistrationNegativetestUserRegistrationNegativetestUserRegistrationNegative");
        createdUser.setPassword("validpwd");
        User actualUser = userService.register(createdUser);
//        Assertions.assertNull(actualUser);
        Assertions.assertEquals(actualUser, new User());

    }
    @Test
    public void testUserRegistrationNegativeFoundUsername() {
        User createdUser = new User();
        createdUser.setId(1);
        createdUser.setUsername("foundUsername");
        createdUser.setPassword("validpwd");
        when(userDao.getUserByUsername(createdUser.getUsername())).thenReturn(null);
        User actualUser = userService.register(createdUser);
        Mockito.verify(this.userDao, times(1)).getUserByUsername(createdUser.getUsername());
        Assertions.assertNull(actualUser);

    }

    @Test
    public void testUserAuthenticatePositive(){
        User userReturn = new User();
        userReturn.setUsername("valid");
        userReturn.setPassword("valid");
        UsernamePasswordAuthentication userAuth = new UsernamePasswordAuthentication();
        userAuth.setUsername("testUserAuthenticatePositive");
        userAuth.setPassword("validpwd");

        when(this.userDao.getUserByUsername(userAuth.getUsername())).thenReturn(userReturn);
        User userActual = this.userService.authenticate(userAuth);
        Assertions.assertEquals(userReturn, userActual);
        verify(userDao, times(1)).getUserByUsername(userAuth.getUsername());

    }
    @Test
    public void testUserAuthenticateNegativeLongCharacters() {
        UsernamePasswordAuthentication userAuth = new UsernamePasswordAuthentication();
        userAuth.setUsername("testUserAuthenticateNegativetestUserAuthenticateNegativetestUserAuthenticateNegative");
        userAuth.setPassword("validpwd");

        User userActual = this.userService.authenticate(userAuth);
        Assertions.assertNull(userActual);

    }
    @Test
    public void testUserAuthenticateNegativeFoundNoUsername() {
        User userReturn = new User();

        UsernamePasswordAuthentication userAuth = new UsernamePasswordAuthentication();
        userAuth.setUsername("invalid");
        userAuth.setPassword("validpwd");

        when(this.userDao.getUserByUsername(userAuth.getUsername())).thenReturn(userReturn);
        User userActual = this.userService.authenticate(userAuth);
        Assertions.assertEquals(userReturn, userActual);
        verify(userDao, times(1)).getUserByUsername(userAuth.getUsername());
    }

}
