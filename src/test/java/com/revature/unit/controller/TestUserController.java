package com.revature.unit.controller;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserController {
    @Mock
    public UserService userService;
    @InjectMocks
    public UserController userController;

//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }

    private final Context ctx = mock(Context.class);
//    public UserController userController;
    @BeforeEach
    public void setup(){
        userController = new UserController(userService);
    }

    @Test
    public void testUserRegisterPositive(){
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 999999);
        User userRequest = new User();
        userRequest.setUsername("controllerTest");
        userRequest.setPassword("valid");

        User userReturn = new User();
        userReturn.setId(1);
        userReturn.setUsername("controllerTest");
//        userReturn.setPassword("valid");

        when(ctx.bodyAsClass(User.class)).thenReturn(userRequest);
        when(userService.register(userRequest)).thenReturn(userReturn);
        userController.register(ctx);
        Mockito.verify(userService, times(1)).register(userRequest);
//        Assertions.assertEquals(201, ctx.statusCode());
//        verify(ctx).status(201);

    }
}
