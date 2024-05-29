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

public class IntegrationTestControllerMoon {
    Javalin app;
    int planetId;


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
        app = Javalin.create();
        RequestMapper.setUpEndPoints(app);
    }

    @Test
    @Order(1)
    public void testDataSeeding(){
        JavalinTest.test(app, (server, client) -> {

            Map<String, String> userJson = new HashMap<>();
            userJson.put("username", "APIsTestUser");
            userJson.put("password", "validPassword");
            String userString = "{\"username\":\"APIsTestUser\", \"password\":\"validPassword\"}";

//            Map<String, String> planetJson = new HashMap<>();
//            planetJson.put("name", "Earth");
//            planetJson.put("ownerId", "1");
//            String planetString = "{'name':'Earth', 'ownerId': 1}";
            String planetString = "{\"name\":\"Earth\", \"ownerId\": 1}";

            String hostUrl = "http://localhost:"+server.port();

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/register", userJson)) {
                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
                Assertions.assertEquals(201, actualStatusCode);
            }


            // Set up the cookie jar to manage cookies (sessions)
            ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

            CookieJar cookieJar = new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<>();
                }
            };

            OkHttpClient httpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();


            RequestBody userBody = RequestBody.create(
                    userString,
                    MediaType.get("application/json")
            );
            Request loginRequest = new Request.Builder()
                    .url(hostUrl+ "/login")
                    .post(userBody)
                    .build();
            try(Response loginResponse = httpClient.newCall(loginRequest).execute()){
                actualStatusCode = loginResponse.code();
                responseBody = Objects.requireNonNull(loginResponse.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
//                System.out.println(loginResponse);
                Assertions.assertEquals(202, actualStatusCode);
                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                RequestBody planetBody = RequestBody.create(
                        planetString,
                        MediaType.get("application/json")
                );
                Request planetRequest = new Request.Builder()
                        .url(hostUrl+ "/api/planet")
                        .post(planetBody)
                        .build();
                try(Response planetResponse = httpClient.newCall(planetRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    Assertions.assertEquals(201, actualStatusCode);

                    ObjectMapper om = new ObjectMapper();
                    JsonNode jsonNode = om.readTree(responseBody);
                    planetId = jsonNode.get("id").asInt();
                    System.out.println(planetId);
                }
            }

//            try(Response loginResponse = client.post("/login", userJson)){
//                actualStatusCode = loginResponse.code();
//                responseBody = Objects.requireNonNull(loginResponse.body().string());
//                System.out.println(actualStatusCode + " :::: " + responseBody);
//                Assertions.assertEquals(202, actualStatusCode);
//
//                try(Response planetResponse = client.post("/api/planet", planetJson)){
//                    actualStatusCode = planetResponse.code();
//                    responseBody = Objects.requireNonNull(planetResponse.body().string());
//                    System.out.println(actualStatusCode + " :::: " + responseBody);
//                    Assertions.assertEquals(201, actualStatusCode);
//                }
//            }


            Assertions.assertNotNull(null);
        });

    }
    @Test
    @DisplayName("Register::Positive")
    @Order(2)
    public void testMoonCreatePositive() {
//        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        JavalinTest.test(app, (server, client) -> {
            Map<String, String> requestJSON = new HashMap<>();
            requestJSON.put("username", "APIsTestUser");
            requestJSON.put("password", "validPassword");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/api/moon", requestJSON)) {
                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
            }
            Assertions.assertEquals(201, actualStatusCode);
            Assertions.assertNotNull(responseBody);
            System.out.println(responseBody);
        });
    }

    @Test
    @DisplayName("Login::Positive")
    @Order(2)
    public void testUserLoginPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, String> requestJSON = new HashMap<>();
            requestJSON.put("username", "APIsTestUser");
            requestJSON.put("password", "validPassword");
            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/login", requestJSON)) {
                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
            }
            Assertions.assertEquals(202, actualStatusCode);
        });
    }

    @Test
    @DisplayName("Login::Negative - invalid password")
    public void testUserLoginNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, String> requestJSON = new HashMap<>();
            requestJSON.put("username", "APIsTestUser");
            requestJSON.put("password", "invalid");
            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/login", requestJSON)) {
                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
            }
            Assertions.assertEquals(400, actualStatusCode);
        });
    }

    @Test
    @DisplayName("Register::Negative - Blank Input")
    public void testUserRegisterNegativeBlankInput() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, String> requestJSON = new HashMap<>();
            requestJSON.put("username", "           ");
            requestJSON.put("password", "valid");
            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/register", requestJSON)) {
                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body().string());
                System.out.println(actualStatusCode + " :::: " + responseBody);
            }
            Assertions.assertEquals(201, actualStatusCode);
//            Assertions.assertNull(responseBody);
        });
    }

}
