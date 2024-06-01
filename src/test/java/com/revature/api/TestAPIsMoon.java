package com.revature.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;
import com.revature.utilities.RequestMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestAPIsMoon {
    static User user;
    static Planet planet;
    static Moon moon;
    static String hostUrl;
    Javalin app;
    OkHttpClient httpClient;
    ConcurrentHashMap<String, List<Cookie>> cookieStore;


    @BeforeAll
    public static void setupClass() throws SQLException, NullPointerException {
        ConnectionUtil.deleteTables();
        Map<String, Object> data = ConnectionUtil.dataSeeding();
        user = (User) data.get("user");
        planet = (Planet) data.get("planet");
        moon = (Moon) data.get("moon");
    }

    @AfterAll
    public static void teardownClass() throws SQLException{
        ConnectionUtil.deleteTables();
    }


    @BeforeEach
    public void setup() {
        app = Javalin.create();
        RequestMapper.setUpEndPoints(app);

        // Set up the cookie jar to manage cookies (sessions)
        cookieStore = new ConcurrentHashMap<>();

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
        httpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();
    }

    @Test
    public void testCreateMoonPositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
        String moonString = String.format("{\"name\":\"moonTest\", \"myPlanetId\": %d}", planet.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                RequestBody moonBody = RequestBody.create(
                        moonString,
                        MediaType.get("application/json")
                );
                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon")
                        .post(moonBody)
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    Assertions.assertEquals(201, actualStatusCode);
                }
            }
        });
    }

    @Test
    public void testCreateMoonNegative(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
        String moonString = String.format("{\"name\":\"%s\", \"myPlanetId\": %d}", moon.getName(), planet.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                RequestBody moonBody = RequestBody.create(
                        moonString,
                        MediaType.get("application/json")
                );
                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon")
                        .post(moonBody)
                        .build();
//                Assertions.assertThrows(NullPointerException.class, httpClient.newCall(moonRequest)::execute);
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    ObjectMapper om = new ObjectMapper();
                    Moon moonGot = om.readValue(responseBody, Moon.class);

                    Assertions.assertEquals(201, actualStatusCode);
                    Assertions.assertEquals(moonGot, new Moon());
                }
//                catch (Exception e){
//                    Assertions.assertEquals(e, NullPointerException.class);
//                }
            }
        });
    }

    @Test
    @Order(100)
    public void testDeleteMoonPositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

//                RequestBody moonBody = RequestBody.create(
//                        moonString,
//                        MediaType.get("application/json")
//                );
                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon/"+ moon.getId())
                        .delete()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
//                    ObjectMapper om = new ObjectMapper();
//                    boolean deleted = om.readValue(responseBody, Boolean.class);

                    Assertions.assertEquals(202, actualStatusCode);
//                    Assertions.assertTrue(deleted);
                }
            }
        });
    }
    @Test
    public void testDeleteMoonNegativeNonExistent(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
//        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon/0")
                        .delete()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
//                    ObjectMapper om = new ObjectMapper();
//                    boolean deleted = om.readValue(responseBody, Boolean.class);

                    Assertions.assertEquals(202, actualStatusCode);
//                    Assertions.assertFalse(deleted);

                }
            }
        });
    }
    @Test
    public void testGetPlanetMoonsPositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
//        String planetString = String.format("{\"id\":\"%d\"}", planet.getId());
//        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/planet/"+ planet.getId()+ "/moon")
                        .get()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    ObjectMapper om = new ObjectMapper();
                    List<Moon> moons = om.readValue(responseBody, new TypeReference<List<Moon>>(){});

                    Assertions.assertEquals(200, actualStatusCode);
                    Assertions.assertFalse(moons.isEmpty());

                }
            }
        });
    }
    @Test
    public void testGetAllMoonsPositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
//        String planetString = String.format("{\"id\":\"%d\"}", planet.getId());
//        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon")
                        .get()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    ObjectMapper om = new ObjectMapper();
                    List<Moon> moons = om.readValue(responseBody, new TypeReference<List<Moon>>(){});

                    Assertions.assertEquals(200, actualStatusCode);
                    Assertions.assertFalse(moons.isEmpty());
                }
            }
        });
    }
    @Test
    @Order(10)
    public void testGetMoonByNamePositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
//        String planetString = String.format("{\"id\":\"%d\"}", planet.getId());
//        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon/name/"+ moon.getName())
                        .get()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    ObjectMapper om = new ObjectMapper();
                    Moon moonGot = om.readValue(responseBody, Moon.class);

                    Assertions.assertEquals(200, actualStatusCode);
                    Assertions.assertEquals(moonGot, moon);
                }
            }
        });
    }
    @Test
    public void testGetMoonByIdPositive(){
        String userString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", user.getUsername(), user.getPassword());
//        String planetString = String.format("{\"id\":\"%d\"}", planet.getId());
//        String moonString = String.format("{\"id\":\"%d\"}", moon.getId());
        JavalinTest.test(app, (server, client)->{
            int actualStatusCode;
            String responseBody;
            String hostUrl = "http://localhost:"+server.port();
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
                Assertions.assertEquals(202, actualStatusCode);

                // Extract session cookie
                List<Cookie> cookies = cookieStore.get("localhost");
                org.assertj.core.api.Assertions.assertThat(cookies).isNotNull();
                org.assertj.core.api.Assertions.assertThat(cookies.size()).isGreaterThan(0);

                Request moonRequest = new Request.Builder()
                        .url(hostUrl+ "/api/moon/id/"+ moon.getId())
                        .get()
                        .build();
                try(Response planetResponse = httpClient.newCall(moonRequest).execute()){
                    actualStatusCode = planetResponse.code();
                    responseBody = Objects.requireNonNull(planetResponse.body().string());
                    System.out.println(actualStatusCode + " :::: " + responseBody);
                    ObjectMapper om = new ObjectMapper();
                    Moon moonGot = om.readValue(responseBody, Moon.class);

                    Assertions.assertEquals(200, actualStatusCode);
                    Assertions.assertEquals(moonGot, moon);
                }
            }
        });
    }


}
