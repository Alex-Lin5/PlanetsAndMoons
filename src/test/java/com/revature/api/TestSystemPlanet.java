package com.revature.api;

import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestSystemPlanet {
    public Javalin app;

    @BeforeEach
    public void setup(){
        app = Javalin.create();
        RequestMapper.setUpEndPoints(app);
        // login first
//        JavalinTest.test(app, (server, client) -> {
//            Map<String,String> requestJSON=new HashMap<>();
//            requestJSON.put("username","test");
//            requestJSON.put("password","test");
//            client.post("/login", requestJSON);
//        });

    }

    @Test
    public void testGetAllPlanets(){
        JavalinTest.test(app, (server, client) ->{
            Map<String,String> requestJSON=new HashMap<>();
            requestJSON.put("username","test");
            requestJSON.put("password","test");

            int statusCode;
            String responseBody;
            try(
                    Response login = client.post("/login", requestJSON);
                    Response response = client.get("/api/planets")){
                statusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(statusCode+" :::: "+responseBody);

            }
            Assertions.assertEquals(200, statusCode);
        });
    }
}
