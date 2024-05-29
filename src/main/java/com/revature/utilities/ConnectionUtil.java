package com.revature.utilities;

import com.revature.MainDriver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionUtil {
    public static Connection createConnection() throws SQLException {
        InputStream props = MainDriver.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            properties.load(props);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean useTest = Boolean.parseBoolean(properties.getProperty("test-mode"));
        if (useTest) {
            return DriverManager.getConnection("jdbc:sqlite:src/test/resources/PlanetariumTest.db");
        }
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/planetarium.db");
    }
    public static void deleteTables() throws SQLException{
        try(Connection connection = ConnectionUtil.createConnection();){
            List<String> tables = new ArrayList<>();
            tables.add("users");
            tables.add("planets");
            tables.add("moons");
            for(String t: tables){
                String sql = String.format("DELETE FROM %s", t);
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.executeUpdate();
            }
            System.out.println("Database table rows are deleted.");
        } catch (Exception e){
            System.err.println("Database table rows cannot be deleted. "+ e.getMessage());
        }
    }
}
