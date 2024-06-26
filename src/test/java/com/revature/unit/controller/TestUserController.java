package com.revature.unit.controller;

import com.revature.controller.UserController;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class TestUserController {

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    private final Context ctx = mock(Context.class);
    @BeforeEach
    public void setup(){
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName("Register::Positive")
    public void testUserRegisterPositive() {
        User userInput = new User();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("valid");
        User userReturn = new User();
        userReturn.setId(100);
        userReturn.setUsername("testUserRegisterPositive");
        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(User.class)).thenReturn(userInput);
        Mockito.when(userService.register(any())).thenReturn(userReturn);
        Mockito.when(ctx.json(userReturn)).thenReturn(ctx);
//        Mockito.when(ctx.status()).thenReturn(HttpStatus.forStatus(201));
//        Mockito.doNothing().when(ctx.json(new Object()));
        userController.register(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx).status(201);
        Mockito.verify(ctx).json(userReturn);
    }

    @Test
    @DisplayName("Login::Positive")
    public void testUserLoginPositive() {
        UsernamePasswordAuthentication userInput = new UsernamePasswordAuthentication();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("valid");
        User userReturn = new User();
        userReturn.setId(100);
        userReturn.setUsername("testUserRegisterPositive");
        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(UsernamePasswordAuthentication.class)).thenReturn(userInput);
        Mockito.when(userService.authenticate(any())).thenReturn(userReturn);
        userController.authenticate(ctx);
        System.out.println(ctx.body());
        Mockito.verify(ctx).status(202);
        Mockito.verify(ctx).json(userReturn);
    }

    @Test
    @DisplayName("Login::Negative - invalid password")
    public void testUserLoginNegative() {
        UsernamePasswordAuthentication userInput = new UsernamePasswordAuthentication();
        userInput.setUsername("testUserRegisterPositive");
        userInput.setPassword("invalid");
//        User userReturn = new User();
//        userReturn.setId(100);
//        userReturn.setUsername("testUserRegisterPositive");
//        userReturn.setPassword("valid");
        Mockito.when(ctx.bodyAsClass(UsernamePasswordAuthentication.class)).thenReturn(userInput);
        Mockito.when(userService.authenticate(any())).thenReturn(null);
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
        Mockito.when(userService.register(any())).thenReturn(userReturn);
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
        Mockito.when(userService.register(any())).thenReturn(userReturn);
//        Mockito.when(ctx.json(null)).thenReturn(ctx);
        Assertions.assertThrows(NullPointerException.class, ()->{
            userController.register(ctx);
        });
        System.out.println(ctx.body());
    }
}
