package com.revature.unit.controller;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestUserController {
    @Mock
    public UserService service;
    @InjectMocks
    public UserController controller;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserAuthenticateSuccess(){
        Context ctx = null;
//        ctx.body() = "";

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

        Mockito.when(this.service.authenticate(userauth)).thenReturn(uret);
        this.controller.authenticate(ctx);


    }
}
