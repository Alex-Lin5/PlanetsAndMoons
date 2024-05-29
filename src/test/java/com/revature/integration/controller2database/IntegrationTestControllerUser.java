package com.revature.integration.controller2database;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTestControllerUser {
    UserController userController;
    UserService userService;
    UserDao userDao;
    private final Context ctx = mock(Context.class);

    @BeforeAll
    public static void setupClass() throws SQLException {
        ConnectionUtil.deleteTables();
    }

    @AfterAll
    public static void teardownClass() throws  SQLException{
        ConnectionUtil.deleteTables();
    }

    @BeforeEach
    public void setup(){
        userDao = new UserDao();
        userService = new UserService(userDao);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName("Register::Positive")
    @Order(1)
    public void testUserRegisterPositive() {
        User userInput = new User();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("valid");
        User userReturn = new User();
//        userReturn.setId(100);
        userReturn.setUsername("testUserRegisterPositive");
        userReturn.setPassword("valid");
        Mockito.when(ctx.body()).thenReturn(String.valueOf(userInput));
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(userInput);
        Mockito.when(ctx.json(any())).thenReturn(ctx);
        userController.register(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx, Mockito.times(1)).bodyAsClass(User.class);
//        Mockito.verify(ctx).json(userReturn);
        Mockito.verify(ctx).status(201);
    }

    @Test
    @DisplayName("Login::Positive")
    @Order(2)
    public void testUserLoginPositive() {
        UsernamePasswordAuthentication userInput = new UsernamePasswordAuthentication();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("valid");
        User userReturn = new User();
//        userReturn.setId(100);
        userReturn.setUsername("testUserRegisterPositive");
        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(UsernamePasswordAuthentication.class)).thenReturn(userInput);
//        Mockito.when(userService.authenticate(any())).thenReturn(userReturn);
        userController.authenticate(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx).status(202);
//        Mockito.verify(ctx).json(userReturn);
    }

    @Test
    @DisplayName("Login::Negative - invalid password")
    public void testUserLoginNegative() {
        UsernamePasswordAuthentication userInput = new UsernamePasswordAuthentication();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("invalid");
        User userReturn = new User();
        userReturn.setId(100);
        userReturn.setUsername("testUserRegisterPositive");
        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(UsernamePasswordAuthentication.class)).thenReturn(userInput);
        userController.authenticate(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx).status(400);
//        Mockito.verify(ctx).json();

    }

    @Test
    @DisplayName("Register::Negative - Blank Input")
    public void testUserRegisterNegativeBlankInput() {
        User userInput = new User();
        userInput.setUsername("         ");
        userInput.setPassword("valid");
        User userReturn = new User();
//        userReturn.setId(100);
//        userReturn.setUsername("testUserRegisterPositive");
//        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(userInput);
        Mockito.when(ctx.json(userReturn)).thenReturn(ctx);
        userController.register(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx).status(201);
        Mockito.verify(ctx).json(userReturn);

    }
    @Test
    @DisplayName("Register::Negative - Throw NullPointerException")
    public void testUserRegisterNegativeThrowException() {
        User userInput = new User();
        userInput.setUsername("         ");
        userInput.setPassword("valid");
        User userReturn = null;
//        userReturn.setId(100);
//        userReturn.setUsername("testUserRegisterPositive");
//        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(userInput);
        Assertions.assertThrows(NullPointerException.class, ()->{
            userController.register(ctx);
        });
        System.out.println(ctx.body());
    }

}
