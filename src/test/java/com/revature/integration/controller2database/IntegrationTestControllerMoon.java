package com.revature.integration.controller2database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTestControllerMoon {
//    Javalin app;
//    int planetId;


    @BeforeAll
    public static void setupClass() throws SQLException {
        ConnectionUtil.deleteTables();
    }

    @AfterAll
    public static void teardownClass() throws  SQLException{
        ConnectionUtil.deleteTables();
    }


    @BeforeEach
    public void setup() throws SQLException {
//        app = Javalin.create();
//        RequestMapper.setUpEndPoints(app);
    }


}
