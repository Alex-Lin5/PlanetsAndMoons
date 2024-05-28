package com.revature.utilities;

import com.revature.MainDriver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public static void deleteTable() throws SQLException{
        try(Connection connection = ConnectionUtil.createConnection();){
            String deleteUserQuery = "DELETE FROM users";
            String deletePlanetQuery = "DELETE FROM planets";
            String deleteMoonQuery = "DELETE FROM moons";
            PreparedStatement statement1 = connection.prepareStatement(deleteUserQuery);
            PreparedStatement statement2 = connection.prepareStatement(deletePlanetQuery);
            PreparedStatement statement3 = connection.prepareStatement(deleteMoonQuery);
            statement1.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
            System.out.println("Database table rows are deleted.");
        } catch (Exception e){
            System.err.println("Database table rows cannot be deleted. "+ e.getMessage());
        }
    }
}
