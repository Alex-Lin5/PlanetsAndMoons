package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons";
			PreparedStatement ps = connection.prepareStatement(sql);
			List<Moon> Moons = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Moon foundMoon = new Moon();
				foundMoon.setId(rs.getInt("id"));
				foundMoon.setName(rs.getString("name"));
				foundMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				Moons.add(foundMoon);
			}
			return Moons;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Moon getMoonByName(String moonName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Moon foundMoon = new Moon();
				foundMoon.setId(rs.getInt("id"));
				foundMoon.setName(rs.getString("name"));
				foundMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				return foundMoon;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Moon getMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Moon foundMoon = new Moon();
				foundMoon.setId(rs.getInt("id"));
				foundMoon.setName(rs.getString("name"));
				foundMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				return foundMoon;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public Moon createMoon(Moon m) {
		try(Connection connection = ConnectionUtil.createConnection()){
			Moon createdMoon = new Moon();
			createdMoon.setId(0);

			// if it is existed in orbiting planet
			String nonex = "select * from moons where myPlanetId = ? and name = ?";
			PreparedStatement psn = connection.prepareStatement(nonex);
			psn.setInt(1, m.getMyPlanetId());
			psn.setString(2, m.getName());
			ResultSet rsn = psn.executeQuery();
			if(rsn.next()) return createdMoon;

			// if orbiting planet is existed
			String nopl = "select * from planets where id = ?";
			PreparedStatement pspl = connection.prepareStatement(nopl);
			pspl.setInt(1, m.getMyPlanetId());
			ResultSet rspl = pspl.executeQuery();
			if(!rspl.next()) return createdMoon;

			// all correct, insert into database
			String sql = "insert into moons (name, myPlanetId) values (?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setInt(2, m.getMyPlanetId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.first()){
//				Moon createdMoon = new Moon();
				createdMoon.setId(rs.getInt("id"));
				createdMoon.setName(rs.getString("name"));
				createdMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				return createdMoon;
			}
			return null;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}

	public boolean deleteMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int val = ps.executeUpdate();
			return val>0;
		} catch (SQLException e){
			System.out.println(e);
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			List<Moon> Moons = new ArrayList<>();
			String sql = "select * from moons where myPlanetId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Moon foundMoon = new Moon();
				foundMoon.setId(rs.getInt("id"));
				foundMoon.setName(rs.getString("name"));
				foundMoon.setMyPlanetId(rs.getInt("myPlanetId"));
				Moons.add(foundMoon);
			}
			return Moons;
		} catch (SQLException e){
			System.out.println(e);
			return null;
		}
	}
}
