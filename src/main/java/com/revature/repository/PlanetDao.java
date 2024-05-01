package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets";
			PreparedStatement ps = connection.prepareStatement(sql);
			List<Planet> Planets = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Planet foundPlanet = new Planet();
				foundPlanet.setId(rs.getInt("id"));
				foundPlanet.setName(rs.getString("name"));
				foundPlanet.setOwnerId(rs.getInt("ownerId"));
				Planets.add(foundPlanet);
			}
			return Planets;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Planet getPlanetByName(String planetName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Planet foundPlanet = new Planet();
				foundPlanet.setId(rs.getInt("id"));
				foundPlanet.setName(rs.getString("name"));
				foundPlanet.setOwnerId(rs.getInt("ownerId"));
				return foundPlanet;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Planet getPlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Planet foundPlanet = new Planet();
				foundPlanet.setId(rs.getInt("id"));
				foundPlanet.setName(rs.getString("name"));
				foundPlanet.setOwnerId(rs.getInt("ownerId"));
				return foundPlanet;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Planet createPlanet(Planet p) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "insert into planets (name, ownerId) values (?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getOwnerId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				Planet createdPlanet = new Planet();
				createdPlanet.setId(rs.getInt("id"));
				createdPlanet.setName(rs.getString("name"));
				createdPlanet.setOwnerId(rs.getInt("ownerId"));
				return createdPlanet;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public boolean deletePlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rows = ps.executeUpdate();
			return rows>0;
		} catch (SQLException e){
			System.out.println(e);
			return false;
		}
	}
}
