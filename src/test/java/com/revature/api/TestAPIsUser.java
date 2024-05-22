package com.revature.api;

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
import java.util.concurrent.ThreadLocalRandom;

public class TestAPIsUser {
    public Javalin app;
    public Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
//        connection = ConnectionUtil.createConnection();
        app = Javalin.create();
        RequestMapper.setUpEndPoints(app);
    }

    @Test
    public void testUserLoginPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","test");
            requestJSON.put("password","test");
            int actualStatusCode;
            String responseBody;
            try(Response response=client.post("/login",requestJSON)){
                actualStatusCode=response.code();
                responseBody= Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode+" :::: "+responseBody);
            }
            Assertions.assertEquals(202,actualStatusCode);
        });
    }
    @Test
    public void testUserLoginNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","test");
            requestJSON.put("password","invalid");
            int actualStatusCode;
            String responseBody;
            try(Response response=client.post("/login",requestJSON)){
                actualStatusCode=response.code();
                responseBody= Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode+" :::: "+responseBody);
            }
            Assertions.assertEquals(400,actualStatusCode);
        });
    }
    @Test
    public void testUserRegisterPositive(){
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        JavalinTest.test(app, (server, client)->{
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","IntegrationTest"+randomNum);
            requestJSON.put("password","validpwd");

//            User user = new User();
//            user.
            int actualStatusCode;
            String responseBody;
            try(Response response=client.post("/register",requestJSON)){
                actualStatusCode=response.code();
                responseBody= Objects.requireNonNull(response.body().string());
//                User userReturn = Objects.requireNonNull(response.bo);
                System.out.println(actualStatusCode+" :::: "+responseBody);
            }
            Assertions.assertEquals(201,actualStatusCode);
            Assertions.assertNotNull(responseBody);
        });
    }
    @Test
    public void testUserRegisterNegativeBlankInput(){
        JavalinTest.test(app, (server, client)->{
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","     ");
            requestJSON.put("password","valid");
            int actualStatusCode;
            String responseBody;
            try(Response response=client.post("/register",requestJSON)){
                actualStatusCode=response.code();
                responseBody= Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode+" :::: "+responseBody);
            }
            Assertions.assertEquals(201,actualStatusCode);
//            Assertions.assertEquals(responseBody, new User().toString());
        });
    }
}
