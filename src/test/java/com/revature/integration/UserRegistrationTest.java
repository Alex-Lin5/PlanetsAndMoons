package com.revature.integration;

import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRegistrationTest {
    public Connection connection;
    public Javalin app;

    @BeforeEach
    public void setup() throws SQLException{
        connection = ConnectionUtil.createConnection();
        app = Javalin.create();
        RequestMapper.setUpEndPoints(app);
    }

    @Test
    public void createUserPositive(){
        JavalinTest.test(app, (server, client)->{
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","newuser");
            requestJSON.put("password","validpwd");
            int actualStatusCode;
            String responseBody;
            try(Response response=client.post("/register",requestJSON)){
                actualStatusCode=response.code();
                responseBody= Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode+" :::: "+responseBody);
            }
            Assertions.assertEquals(201,actualStatusCode);
        });
    }
}
